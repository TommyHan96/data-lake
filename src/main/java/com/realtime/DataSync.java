package com.realtime;

import com.realtime.constant.KafkaConstConfig;
import com.realtime.constant.MysqlConstConfig;
import com.realtime.function.CustomerDeserialization;
import com.realtime.function.OverridingTopicSchema;
import com.realtime.utils.FlinkKafkaManager;
import com.ververica.cdc.connectors.mysql.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.DebeziumSourceFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Properties;

public class DataSync {

    private static final Logger logger = LogManager.getLogger(DataSync.class);

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        DebeziumSourceFunction<String> sourceFunction =
                MySqlSource.<String>builder()
                        .hostname(MysqlConstConfig.HOST)
                        .port(MysqlConstConfig.PORT)
                        .databaseList(MysqlConstConfig.DATABASE)
                        .username(MysqlConstConfig.USER)
                        .password(MysqlConstConfig.PASSWORD)
                        .startupOptions(StartupOptions.latest())
                        .deserializer(new CustomerDeserialization())
                        .build();

        DataStreamSource<String> mysqlStream = env.addSource(sourceFunction);

        env.enableCheckpointing(3000);


        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstConfig.BROKERS);
        properties.put("transaction.timeout.ms", "720000");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");


        FlinkKafkaManager kafkaManager = new FlinkKafkaManager(properties);
        FlinkKafkaProducer<String> kafkaSink = kafkaManager.createDynamicFlinkProducer(KafkaConstConfig.BROKERS, new OverridingTopicSchema());


        mysqlStream.addSink(kafkaSink);
        mysqlStream.print();
        env.execute();
    }

}

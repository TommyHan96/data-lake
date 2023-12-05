package com.realtime;

import com.realtime.function.CustomerDeserialization;
import com.realtime.function.OverridingTopicSchema;
import com.realtime.utils.FlinkKafkaManager;
import com.realtime.utils.RealtimeProperties;
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
import static com.realtime.constant.ConstString.*;

public class DataSync {

    private static final Logger logger = LogManager.getLogger(DataSync.class);

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);
        RealtimeProperties loader = new RealtimeProperties();
        Properties properties = loader.getProperties();

        DebeziumSourceFunction<String> sourceFunction =
                MySqlSource.<String>builder()
                        .hostname(properties.getProperty(MYSQL_HOST))
                        .port(Integer.parseInt(properties.getProperty(MYSQL_PORT)))
                        .databaseList(properties.getProperty(MYSQL_DATABASE))
                        .username(properties.getProperty(MYSQL_USER))
                        .password(properties.getProperty(MYSQL_PASSWORD))
                        .startupOptions(StartupOptions.latest())
                        .deserializer(new CustomerDeserialization())
                        .build();

        DataStreamSource<String> mysqlStream = env.addSource(sourceFunction);

        env.enableCheckpointing(3000);

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getProperty(KAFKA_BROKERS));
        properties.put("transaction.timeout.ms", properties.getProperty("kafka.transaction.timeout.ms"));
        properties.put("key.serializer", properties.getProperty("kafka.key.serializer"));
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, properties.getProperty("kafka.value.serializer"));


        FlinkKafkaManager kafkaManager = new FlinkKafkaManager(properties);
        FlinkKafkaProducer<String> kafkaSink = kafkaManager.createDynamicFlinkProducer(properties.getProperty(KAFKA_BROKERS), new OverridingTopicSchema());


        mysqlStream.addSink(kafkaSink);
        mysqlStream.print();
        env.execute();
    }

}

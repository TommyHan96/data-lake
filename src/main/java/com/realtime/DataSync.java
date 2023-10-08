package com.realtime;

import com.realtime.constant.KafkaConstConfig;
import com.realtime.constant.MysqlConstConfig;
import com.realtime.function.CustomerDeserialization;
import com.ververica.cdc.connectors.mysql.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.DebeziumSourceFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

public class DataSync {

  public static void main(String[] args) throws Exception {
    //

    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

    env.setParallelism(10);
    DebeziumSourceFunction<String> sourceFunction =
        MySqlSource.<String>builder()
            .hostname(MysqlConstConfig.HOST)
            .port(MysqlConstConfig.PORT)
            .databaseList(MysqlConstConfig.DATABASE) // 数据库名
            .username(MysqlConstConfig.USER)
            .password(MysqlConstConfig.PASSWORD)
            .startupOptions(StartupOptions.latest())
            .deserializer(new CustomerDeserialization())
            .build();

    DataStreamSource<String> mysqlStream = env.addSource(sourceFunction);
    mysqlStream.print();

    env.enableCheckpointing(3000);
    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstConfig.BROKERS);
    properties.setProperty("transaction.timeout.ms", "720000");

    FlinkKafkaProducer<String> kafkaProducer =
        new FlinkKafkaProducer<>(
            KafkaConstConfig.BROKERS, KafkaConstConfig.TOPIC, new SimpleStringSchema());

    mysqlStream.addSink(kafkaProducer);

    env.execute();
  }
}

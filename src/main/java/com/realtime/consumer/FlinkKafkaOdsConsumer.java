package com.realtime.consumer;

import com.realtime.constant.ConstString;
import com.realtime.utils.KafkaSchemaParser;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

public class FlinkKafkaOdsConsumer {
  public static void main(String[] args) {
    //

    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    // set properties
    Properties properties = new Properties();
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ConstString.KAFKA_BROKERS);
    properties.setProperty("group.id", "flink-ods-trans");
    properties.setProperty("enable.auto.commit", "true");
    properties.setProperty("auto.commit.interval.ms", "2000");

    // consumer
    FlinkKafkaConsumer consumer =
        new FlinkKafkaConsumer(
            ConstString.DEFAULT_TOPIC, new SimpleStringSchema(), properties);

    // checkpoint
    env.enableCheckpointing(5000);
    KafkaSchemaParser kafkaSchemaParser = new KafkaSchemaParser();

    DataStream<String> dataStream = env.addSource(consumer).map(new MapFunction() {
      @Override
      public Object map(Object o) throws Exception {
        String kafkaString = (String) o;
        return kafkaSchemaParser.parseSchema(kafkaString);
      }
    });
  }
}

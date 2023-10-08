package com.realtime.test;

import com.realtime.constant.KafkaConstConfig;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

public class KafkaTest {

    public static void main(String[] args) throws Exception{
        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        //kafka的连接
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,KafkaConstConfig.BROKERS);
        properties.setProperty("auto.offset.reset","latest");

        DataStream<String> dataStream = env.addSource( new FlinkKafkaConsumer<String>(KafkaConstConfig.TOPIC,new SimpleStringSchema(),properties));

        //打印输出
        dataStream.print();

        // 执行
        env.execute();
    }
}

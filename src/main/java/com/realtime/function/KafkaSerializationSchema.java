package com.realtime.function;

import org.apache.kafka.clients.producer.ProducerRecord;

import javax.annotation.Nullable;
import java.nio.charset.Charset;

public class KafkaSerializationSchema implements org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema<String> {
    private String topic;
    private String charset;

    public KafkaSerializationSchema(String topic) {
        this.topic = topic;
        this.charset = "UTF-8";
    }

    public KafkaSerializationSchema(String topic, String charset) {
        this.topic = topic;
        this.charset = charset;
    }

    @Override
    public ProducerRecord<byte[], byte[]> serialize(String s, @Nullable Long aLong) {
        return new ProducerRecord<>(topic, s.getBytes(Charset.forName(charset)));
    }
}

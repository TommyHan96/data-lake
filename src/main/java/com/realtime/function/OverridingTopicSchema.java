package com.realtime.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realtime.DataSync;
import com.realtime.domain.CDCJson;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.flink.streaming.connectors.kafka.KafkaContextAware;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.annotation.Nullable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Getter
public class OverridingTopicSchema implements KafkaSerializationSchema<String>, KafkaContextAware<String> {
    private static final Logger logger = LogManager.getLogger(OverridingTopicSchema.class);
    private String topic;

    public OverridingTopicSchema() {
        super();
    }


    @SneakyThrows
    @Override
    public ProducerRecord<byte[], byte[]> serialize(String json, @Nullable Long aLong) {
        return new ProducerRecord<>(topic, json.getBytes(StandardCharsets.UTF_8));
    }


    /**
     * parse json and get mysql table name as kafka topic
     *
     * @param element
     * @return
     */
    @SneakyThrows
    @Override
    public String getTargetTopic(String element) {
        ObjectMapper objectMapper = new ObjectMapper();
        this.topic = objectMapper.readValue(element, CDCJson.class).getSource().getTable();
        return this.topic;
    }
}

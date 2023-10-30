package com.realtime.utils;

import com.realtime.DataSync;
import com.realtime.domain.CDCJson;
import com.realtime.function.OverridingTopicSchema;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static com.realtime.constant.KafkaConstConfig.DEFAULT_TOPIC;
import static org.apache.flink.kafka.shaded.org.apache.kafka.clients.producer.ProducerConfig.ACKS_CONFIG;

public class KafkaManager {
    private static final Logger logger = LogManager.getLogger(KafkaManager.class);
    private Properties properties;

    private AdminClient ac;


    public KafkaManager(Properties properties) {
        this.properties = properties;
        ac = AdminClient.create(this.properties);
    }


    public Boolean topicExists(String topic) {
        try {
            return ac.listTopics().names().get().contains(topic);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Boolean createTopicIfNotExist(String topic, int partitions, short replica) {
        if (!topicExists(topic)) {
            NewTopic newTopic = new NewTopic(topic, partitions, replica);
            ac.createTopics(Collections.singleton(newTopic));
            ac.close();
            return true;
        } else {
            logger.warn("topic already exists");
            ac.close();
            return false;
        }
    }

    /**
     * create dynamic flink producer
     * use DEFAULT_TOPIC: ods_test
     *
     * @param brokers
     * @param kafkaSerializationSchema
     * @return
     */
    public FlinkKafkaProducer<String> createDynamicFlinkProducer(String brokers, KafkaSerializationSchema<String> kafkaSerializationSchema) {
        if (StringUtils.isEmpty(brokers)) {
            throw new IllegalArgumentException("bootstrap server is necessary");
        }

        this.properties.put(ACKS_CONFIG, "all");
        return new FlinkKafkaProducer<String>(DEFAULT_TOPIC, kafkaSerializationSchema, this.properties, FlinkKafkaProducer.Semantic.EXACTLY_ONCE);
    }


    /**
     * {"op":"c","before":{},"source":{"database":"datawarehouse","table":"ods_yx_base_department"},"after":{"bu_name":"广告部","second_department_name":"开发部","third_department_name":"开发部","code":52,"first_department_code":70,"level":"5","second_department_code":64,"bg_code":51358,"bu_code":50046,"bg_name":"销售部","all_parent_department_name":"事业部","is_valid":0,"name":"产品部","first_department_name":"广告部","id":44,"third_department_code":959}}
     *
     * @return
     */
    public CDCJson parseSchema(String str) {
        ObjectMapper objMapper = new ObjectMapper();
        try {
            CDCJson cdcObj = objMapper.readValue(str, CDCJson.class);
            //TODO op equals update delete

            //TODO how to deal with before

            //get source

            //get after
            return cdcObj;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

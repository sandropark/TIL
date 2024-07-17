package com.sandro;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Properties;

import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Slf4j
class ProducerTest {

    static String BOOTSTRAP_SERVERS = "localhost:29092";
    static String TOPIC_NAME = "test";

    @Test
    void produce() throws Exception {
        KafkaProducer<String, MyData> producer = new KafkaProducer<>(build());

        MyData data = new MyData("sandro", 10);
        ProducerRecord<String, MyData> record = new ProducerRecord<>(TOPIC_NAME, data);
        try {
            producer.send(record);
            log.info("data = {}", data);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Properties build() {
        Properties configs = new Properties();
        configs.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configs.put(ACKS_CONFIG, "all");

        return configs;
    }

//    @Test
//    void produceWithKeyValue() throws Exception {
//        for (int i = 0; i < 10; i++) {
//            String data = "This is record " + i;
//            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, Integer.toString(i), data);
//            try {
//                producer.send(record);
//                System.out.println("Send to " + TOPIC_NAME + " | data : " + data);
//                Thread.sleep(1000);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//    }

}
package com.sandro;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Slf4j
class ConsumerTest {

    static String BOOTSTRAP_SERVERS = "localhost:29092";
    static String TOPIC_NAME = "test";
    static String GROUP_ID = "testgroup";

    Properties properties = build();
    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

    public static Properties build() {
        Properties configs = new Properties();
        configs.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        configs.put(GROUP_ID_CONFIG, GROUP_ID);
        configs.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return configs;
    }

    @Test
    void consume() throws Exception {
        consumer.subscribe(List.of(TOPIC_NAME));

        while (true) {
            consumer.poll(Duration.ofSeconds(1))
                    .iterator()
                    .forEachRemaining(record -> System.out.println(record.value()));
        }
    }

    @Test
    void commitSync() throws Exception {
        properties.put(ENABLE_AUTO_COMMIT_CONFIG, false);
        consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(List.of(TOPIC_NAME));

        while (true) {
            consumer.poll(Duration.ofSeconds(1))
                    .iterator()
                    .forEachRemaining(record -> System.out.println(record.value()));
            try {
                consumer.commitSync();
            } catch (CommitFailedException e) {
                System.out.println("commit failed");
            }
        }
    }

    @Test
    void commitSyncArgs() throws Exception {
        properties.put(ENABLE_AUTO_COMMIT_CONFIG, false);
        consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(List.of(TOPIC_NAME));

        HashMap<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();

        int i = 0;

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());

                offsets.put(
                        new TopicPartition(record.topic(), record.partition()),
                        new OffsetAndMetadata(record.offset() + 1)
                );

                try {
                    consumer.commitSync(offsets);
                } catch (CommitFailedException e) {
                    System.out.println("commit failed");
                }

                if (i == 2)
                    throw new RuntimeException();
                i++;
            }
        }
    }

    @Test
    void wakeUp() throws Exception {
        properties.put(ENABLE_AUTO_COMMIT_CONFIG, false);
        consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(List.of(TOPIC_NAME));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> consumer.wakeup()));

        int i = 0;

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());

//                    if (i == 2)
//                        throw new WakeupException();
//                    i++;
                }
                try {
                    consumer.commitSync();
                } catch (CommitFailedException e) {
                    System.out.println("commit failed");
                }
            }
        } catch (WakeupException e) {
            System.out.println("wake up!");
        } finally {
            consumer.commitSync();
            consumer.close();
        }
    }

    @Test
    void wakeUpArgs() throws Exception {
        properties.put(ENABLE_AUTO_COMMIT_CONFIG, false);
        consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(List.of(TOPIC_NAME));

        HashMap<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> consumer.wakeup()));

        int i = 0;

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());

                    offsets.put(
                            new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1)
                    );

                    if (i == 2)
                        throw new WakeupException();
                    i++;
                }
                try {
                    consumer.commitSync();
                } catch (CommitFailedException e) {
                    System.out.println("commit failed");
                }
            }
        } catch (WakeupException e) {
            System.out.println("wake up!");
        } finally {
            consumer.commitSync(offsets);
            consumer.close();
        }
    }

    @Test
    void myDataTest() throws Exception {
        HashMap<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
        Properties properties = build();
        properties.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        KafkaConsumer<String, MyData> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(List.of(TOPIC_NAME));

        Runtime.getRuntime().addShutdownHook(new Thread(consumer::wakeup));

        try {
            while (true) {
                ConsumerRecords<String, MyData> records = consumer.poll(Duration.ofSeconds(1));

                for (ConsumerRecord<String, MyData> record : records) {

                    offsets.put(
                            new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1)
                    );
                    // 로직

                    MyData data = record.value();

                    log.info("data = {}", data);
                }
                try {
                    consumer.commitSync(offsets);
                } catch (CommitFailedException e) {
                    log.error("Commit Failed {}", offsets);
                }
            }
        } catch (WakeupException e) {
            log.warn("exception", e);
        } finally {
            consumer.commitSync(offsets);
            consumer.close();
        }
    }
}

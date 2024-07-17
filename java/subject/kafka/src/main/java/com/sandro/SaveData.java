package com.sandro;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Slf4j
class SaveData {
    static String TOPIC_NAME = "my-computer-metric";
    static String GROUP_ID = "metric-consumers";
    static String BOOTSTRAP_SERVERS = "localhost:29092";
    static int CONSUMER_COUNT = 5;
    static List<ConsumerWorker> workerThreads = new ArrayList<>();

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new ShutdownThread());
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(GROUP_ID_CONFIG, GROUP_ID);
        properties.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ENABLE_AUTO_COMMIT_CONFIG, false);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < CONSUMER_COUNT; i++) {
            ConsumerWorker worker = new ConsumerWorker(properties, TOPIC_NAME, i);
            workerThreads.add(worker);
            executorService.execute(worker);
        }
    }

    static class ShutdownThread extends Thread {
        public void run() {
            workerThreads.forEach(ConsumerWorker::shutdown);
            log.info("bye");
        }
    }
}

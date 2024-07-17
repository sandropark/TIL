package com.sandro.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyKafkaListener {

    @KafkaListener(
            groupId = "test-group",
            topics = {"test"}
    )
    public void listener(ConsumerRecords<String, String> records, Acknowledgment ack, Consumer<String, String> consumer) {
        records.forEach(record -> {
            log.info("record = {}", record);
            commitAsync(consumer);
        });
    }

    private void commitAsync(Consumer<String, String> consumer) {
        consumer.commitAsync((offsets, exception) -> {
            try {
                log.info("Succeeded to commit. offset.size = {}", offsets.size());
            } catch (Exception e) {
                log.error("Failed to commit", exception);
            }
        });
    }
}

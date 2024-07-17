package com.sandro.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
@RequiredArgsConstructor
@Component
public class MyKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload) {
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topic, payload);
        listenableFuture.addCallback(
                result -> {
                    log.info("Succeeded to send");
                },
                ex -> {
                    log.error("Failed to send");
                }
        );
    }
}

package com.sandro.kafka;

import com.sandro.kafka.producer.MyKafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.Set;

import static com.sandro.kafka.KafkaTest.TEST;

@SpringBootTest(classes = KafkaTestContext.class)
@EmbeddedKafka(
        topics = TEST
        ,partitions = 3
        ,brokerProperties = "listeners=PLAINTEXT://localhost:9092"
)
public class KafkaTest {
    public static final String TEST = "test";

    @Autowired
    MyKafkaProducer producer;
    @Autowired
    private EmbeddedKafkaBroker broker;

    @Test
    void test() throws Exception {
        System.out.println("broker.getBrokersAsString() = " + broker.getBrokersAsString());
        System.out.println("topics = " + broker.getTopics());
    }

    @Test
    void test2() throws Exception {
        // Given

        // When
        producer.send(TEST, "안녕");

        // Then
        Thread.sleep(2000);
    }
}

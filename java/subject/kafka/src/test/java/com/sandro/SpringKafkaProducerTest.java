package com.sandro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
class SpringKafkaProducerTest {

    @SpringBootApplication
    static class TestContext {}

    @Autowired
    KafkaTemplate<String, String> template;

    @Test
    void push() throws Exception {
        template.send("horn-push",
                "{\"serviceId\":\"himom-service-id\",\"reservation\":null,\"reservationTime\":null,\"endpoints\":[{\"userId\":\"user1\",\"name\":null,\"recipientType\":null,\"type\":\"SMS\",\"endpoint\":\"010-4378-3313\",\"placeholder\":null}],\"message\":{\"useTemplate\":null,\"title\":\"SMS 발송 테스트\",\"content\":공할 것인가??\",\"placeholder\":null,\"additional\":null,\"optional\":null},\"smsAdditional\":null,\"mailAdditional\":null,\"reservationTimeStr\":null}");
    }

}

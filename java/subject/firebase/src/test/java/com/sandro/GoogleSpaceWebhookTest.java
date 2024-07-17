package com.sandro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

class GoogleSpaceWebhookTest {

    RestTemplate template = new RestTemplate();

    @Test
    void test() throws Exception {
        template.exchange(
                "https://chat.googleapis.com/v1/spaces/AAAAkqlRBGM/messages?key=AIzaSyDdI0hCZtE6vySjMm-WEfRq3CPzqKqqsHI&token=J-Ipk-npLcUiozo6UreKBLeZpHle7CY1JKmKILJjC6I",
                HttpMethod.POST,
                getRequestEntity(),
                String.class);
    }

    private HttpEntity<Body> getRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(new Body("안녕"), headers);
    }

    @Getter
    @AllArgsConstructor
    static class Body {
        private String text;
    }
}

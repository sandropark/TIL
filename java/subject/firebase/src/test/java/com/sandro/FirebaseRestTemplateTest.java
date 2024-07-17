package com.sandro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

class FirebaseRestTemplateTest {

    private final String url = "https://fcm.googleapis.com/fcm/send";
    RestTemplate template = new RestTemplate();

    @Test
    void test() throws Exception {
        ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, getRequestEntity(), String.class);
        System.out.println("response = " + response);
    }

    private HttpEntity<Body> getRequestEntity() {
        return new HttpEntity<>(getBody(), getHeaders());
    }

    private Body getBody() {
        return Body.builder()
                .to("exIxdVBaRxioSlVLpWfDTK:APA91bH38u7lEYMljwLrHNjDFbUnsWoFHifKQF9RL3RuD44Foirk_rkH01nkAPyatbhqXHqGFYMIKHuT7Rcb8xVp3de37btOqoNGP0zgEM9MzaDIRNlWy1HIvGwYW-9GZoz-EtQh4CDg") // Android
                .title("RestTemplate 테스트")
                .content("안녕")
                .type("1")
                .build();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION, "key=AAAAHbu3zsg:APA91bFmhml0ftmcK4yKVfIztLcKEG8foh5mIQeT8n5InC_hxJqMYspkhcunu7oDhreT5LMsJn43lPDzOTCrb4yIKWVAZDf9_5oYjWxce_Xuwkld9YAQBPu1WHh4HsFQVM4NTwtNaOky");
        return headers;
    }


    @Getter
    static class Body {
        private String to;
        private Data data;

        @Builder
        private Body(String to, String title, String content, String type) {
            this.to = to;
            data = new Data(title, content, type);
        }

        @AllArgsConstructor
        @Getter
        static class Data {
            private String title;
            private String content;
            private String type;
        }
    }
}

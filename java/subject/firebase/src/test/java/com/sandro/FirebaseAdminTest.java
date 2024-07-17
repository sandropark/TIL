package com.sandro;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootTest
class FirebaseAdminTest {

    @SpringBootApplication
    static class TestContext {
        @Bean
        public void init() throws IOException {
            FileInputStream serviceAccount = new FileInputStream("/Users/sandro/huray/horn-fcm-key/2.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

            FirebaseApp.initializeApp(options);
        }
    }

    @Test
    void test() throws Exception {
        Message message = Message.builder()
                .setToken("fxMiiKHuQgqd1zMZvGRmrn:APA91bHNTSLW9ws4nHR1iQDZpRJXE1I3-SSCnowjXMqBPKlakGW55ZhXi3tPHQbUUnpnPpd1Y6-i8hej98oA551Q5cawJsJdC67BHewj9wzZQhJobgCGVy7hm0SJnxfYe2CRuNPIBw4A") // Android
//                .setToken("f3895dffe2b0f762bb6c08fb78b20bd0c16ac382a8b18427058a93770f3c16c0")  // IOS
                .putData("title", "Firebase SDK 테스트")
                .putData("content", "안녕")
                .putData("type", "1")
                .build();
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("response = " + response);
    }


}

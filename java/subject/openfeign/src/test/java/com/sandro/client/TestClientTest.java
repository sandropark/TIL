package com.sandro.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TestClientTest {
    @Autowired TestClient client;

    @Test
    void test() throws Exception {
        String result = client.hello();
        assertThat(result).isEqualTo("Hello");
    }

}
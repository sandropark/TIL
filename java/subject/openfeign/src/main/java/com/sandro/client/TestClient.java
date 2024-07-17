package com.sandro.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "TestClient",
        url = "localhost:${server.port}"
)
public interface TestClient {
    @GetMapping("/hello")
    String hello();
}

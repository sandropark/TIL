package com.sandro.controller;

import com.sandro.client.TestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/test")
@RestController
public class TestController2 {

    @Autowired TestClient client;
    @GetMapping("/hello")
    public String hello() {
        log.info("TestController2.hello");
        return client.hello();
    }
}

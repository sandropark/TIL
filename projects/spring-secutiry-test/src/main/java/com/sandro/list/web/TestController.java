package com.sandro.list.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/user")
    public SecurityMessage user(Authentication authentication) {
        return SecurityMessage.builder()
                .message("user page")
                .auth(authentication)
                .build();
    }

    @GetMapping("/admin")
    public SecurityMessage admin(Authentication authentication) {
        return SecurityMessage.builder()
                .message("admin page")
                .auth(authentication)
                .build();
    }

}

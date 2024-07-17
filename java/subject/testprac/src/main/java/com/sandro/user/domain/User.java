package com.sandro.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;

    public User(String name) {
        this.name = name;
    }
}

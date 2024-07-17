package com.sandro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyData {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "MyData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

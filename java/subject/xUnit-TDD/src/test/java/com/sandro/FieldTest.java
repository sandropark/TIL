package com.sandro;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FieldTest {

    static class A {
        private String name;
        private int age;
//        private String book;
    }

    static class B {
        private int age;
        private String name;
//        private String gender;
    }

    @ToString
    @EqualsAndHashCode
    static class FieldInfo {
        private final String name;
        private final Class<?> type;

        public FieldInfo(Field field) {
            name = field.getName();
            type = field.getType();
        }
    }

    @Test
    void test() throws Exception {
        // Given
        List<FieldInfo> aFields = Arrays.stream(A.class.getDeclaredFields())
                .map(FieldInfo::new)
                .collect(Collectors.toList());
        List<FieldInfo> bFields = Arrays.stream(B.class.getDeclaredFields())
                .map(FieldInfo::new)
                .collect(Collectors.toList());
        // When


        // Then
        assertThat(aFields.size()).isEqualTo(bFields.size());
        assertThat(aFields).containsAll(bFields);
    }

}

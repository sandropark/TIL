package com.sandro.unitTest;

import org.junit.jupiter.api.Test;

import static com.sandro.unitTest.StringMethod.isStringLong;
import static org.assertj.core.api.Assertions.assertThat;

class UnitTest {

    @Test
    void test() throws Exception {
        assertThat(isStringLong("abc")).isFalse();
        assertThat(isStringLong("abcdef")).isTrue();
    }

}
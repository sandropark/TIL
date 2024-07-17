package com.sandro.leetcode.valid_parentheses;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidParenthesesTest {

    @Test
    void isValidTest() throws Exception {
        assertThat(isValid("(")).isFalse();
        assertThat(isValid("()")).isTrue();
    }

    private boolean isValid(String s) {
        return false;
    }

}

package com.sandro.leetcode.roman_to_integer;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanToIntegerTest {

    RomanConvertor convertor = new RomanConvertor();

    @Test
    void romanToIntTest() throws Exception {
        assertThat(convertor.romanToInt("I")).isEqualTo(1);
        assertThat(convertor.romanToInt("V")).isEqualTo(5);
        assertThat(convertor.romanToInt("X")).isEqualTo(10);
        assertThat(convertor.romanToInt("L")).isEqualTo(50);
        assertThat(convertor.romanToInt("C")).isEqualTo(100);
        assertThat(convertor.romanToInt("D")).isEqualTo(500);
        assertThat(convertor.romanToInt("M")).isEqualTo(1000);
        assertThat(convertor.romanToInt("II")).isEqualTo(2);
        assertThat(convertor.romanToInt("IV")).isEqualTo(4);
        assertThat(convertor.romanToInt("IX")).isEqualTo(9);
        assertThat(convertor.romanToInt("MCMXCIV")).isEqualTo(1994);
    }

    private static class RomanConvertor {
        private final Map<Character, Integer> map = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);

        private int romanToInt(String s) {
            int num;
            int result = 0;
            int tmp = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                num = map.get(s.charAt(i));
                result = num < tmp ? result - num : result + num;
                tmp = num;
            }
            return result;
        }
    }
}

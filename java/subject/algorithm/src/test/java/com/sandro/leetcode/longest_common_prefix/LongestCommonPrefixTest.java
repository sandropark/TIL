package com.sandro.leetcode.longest_common_prefix;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LongestCommonPrefixTest {

    @Test
    void testLongestCommonPrefix() throws Exception {
        assertThat(longestCommonPrefix("")).isEqualTo("");
        assertThat(longestCommonPrefix("a")).isEqualTo("a");
        assertThat(longestCommonPrefix("", "a")).isEqualTo("");
        assertThat(longestCommonPrefix("a", "a")).isEqualTo("a");
        assertThat(longestCommonPrefix("a", "b")).isEqualTo("");
        assertThat(longestCommonPrefix("ab", "a")).isEqualTo("a");
        assertThat(longestCommonPrefix("flower","flow","flight")).isEqualTo("fl");
        assertThat(longestCommonPrefix("dog","racecar","car")).isEqualTo("");
    }

    private String longestCommonPrefix(String... strings) {
        String preFix = strings[0];
        for (int i = 1; i < strings.length; i++) {
            String str = strings[i];
            if (str.isEmpty()) return "";
            StringBuilder tmp = new StringBuilder();
            for (int j = 0; j < Math.min(preFix.length(), str.length()); j++) {
                if (preFix.charAt(j) == str.charAt(j))
                    tmp.append(preFix.charAt(j));
            }
            preFix = tmp.toString();
        }
        return preFix;
    }

    // 속도가 가장 빠른 알고리즘
//    private String func(String... strings) {
//        String preFix = strings[0];
//        for (int i = 1; i < strings.length; i++)
//            while (strings[i].indexOf(preFix) != 0)
//                preFix = preFix.substring(0, preFix.length() - 1);
//        return preFix;
//    }
}

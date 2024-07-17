package com.sandro.wordwrapper;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WrapperTest {

    @Test
    void test() throws Exception {
        assertWraps(null, 1, "");
        assertWraps("", 1, "");
        assertWraps("x", 1, "x");
        assertWraps("xx", 1, "x\nx");
        assertWraps("xxx", 1, "x\nx\nx");
        assertWraps("x x", 1, "x\nx");
        assertWraps("x xx", 3, "x\nxx");
        assertWraps("four score and seven age our fathers brought forth upon this continent", 7,
                "four\nscore\nand\nseven\nage our\nfathers\nbrought\nforth\nupon\nthis\ncontine\nnt");
    }

    private String wrap(String s, int length) {
        if (s == null) return "";

        if (s.length() <= length)
            return s;
        else {
            int breakPoint = s.lastIndexOf(" ", length);
            if (breakPoint == -1)
                breakPoint = length;
            return s.substring(0, breakPoint) + "\n" + wrap(s.substring(breakPoint).trim(), length);
        }
    }

    private void assertWraps(String s, int length, String expected) {
        assertThat(wrap(s, length)).isEqualTo(expected);
    }

}

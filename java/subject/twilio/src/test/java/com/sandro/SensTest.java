package com.sandro;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SensTest {

    @Test
    void test() throws Exception {
        String str = "🕙";
        String str2 = "🏁";

        System.out.println(Arrays.toString(str.getBytes()));
        System.out.println(Arrays.toString(str2.getBytes()));
    }

}

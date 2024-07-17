package com.sandro.fibonacci;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciTest {

    /**
     * 1. Red -> Green
     */
//    @Test
//    void fibonacci() throws Exception {
//        assertEquals(0, fib(0));
//    }
//
//    private int fib(int n) {
//        return 0;
//    }


    /**
     * 2. Red -> Green
     */
//    @Test
//    void fibonacci() throws Exception {
//        assertEquals(0, fib(0));
//        assertEquals(1, fib(1));
//    }

//    private int fib(int n) {
//        if (n == 0) return 0;
//        return 1;
//    }

    /**
     * 3. Blue
     */
//    @ParameterizedTest
//    @CsvSource(value = {"0, 0", "1, 1"})
//    void fibonacci(int expected, int value) throws Exception {
//        assertEquals(expected, fib(value));
//    }

    /**
     * 4. Red -> Green
     */
    @ParameterizedTest
    @CsvSource(value = {"0, 0", "1, 1", "1, 2", "2, 3"})
    void fibonacci(int expected, int value) throws Exception {
        assertEquals(expected, fib(value));
    }

//    private int fib(int n) {
//        if (n == 0) return 0;
//        if (n <= 2) return 1;
//        return 2;
//    }

    /**
     * 5. Blue
     */
//    private int fib(int n) {
//        if (n == 0) return 0;
//        if (n <= 2) return 1;
//        return 1 + 1;   // Refactor
//    }

    /**
     * 6. Blue
     */
//    private int fib(int n) {
//        if (n == 0) return 0;
//        if (n <= 2) return 1;
//        return fib(n - 1) + 1;   // Refactor
//    }

    /**
     * 7. Blue
     */
//    private int fib(int n) {
//        if (n == 0) return 0;
//        if (n <= 2) return 1;
//        return fib(n - 1) + fib(n - 2);   // Refactor
//    }

    /**
     * 8. Blue
     */
    private int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;   // Refactor
        return fib(n - 1) + fib(n - 2);
    }

}

package com.sandro.primeFactors;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PrimeFactorsTest {

    @Test
    void canFactorIntoPrimes() throws Exception {
        assertPrimeFactors(1, list());
        assertPrimeFactors(2, list(2));
        assertPrimeFactors(3, list(3));
        assertPrimeFactors(4, list(2, 2));
        assertPrimeFactors(5, list(5));
        assertPrimeFactors(6, list(2, 3));
        assertPrimeFactors(7, list(7));
        assertPrimeFactors(8, list(2, 2, 2));
        assertPrimeFactors(9, list(3, 3));
        assertPrimeFactors(2 * 2 * 3 * 5 * 7 * 11 * 13, list(2, 2, 3, 5, 7, 11, 13));
    }

    private List<Integer> of(int n) {
        List<Integer> factors = new ArrayList<>();
        int divider = 2;
        for (; n > 1; divider++)
            for (; n % divider == 0; n /= divider)
                factors.add(divider);
        return factors;
    }

    private void assertPrimeFactors(int n, List<Integer> list) {
        assertThat(of(n)).isEqualTo(list);
    }

    private List<Integer> list(Integer... ints) {
        return List.of(ints);
    }

}
package com.sandro.greedy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GreedyTest {

    @Test
    void greedy() throws Exception {
        Greedy greedy = new Greedy();

        int result = greedy.getResult(4200);
        assertThat(result).isEqualTo(6);

        int result2 = greedy.getResult(4790);
        assertThat(result2).isEqualTo(12);
    }

}
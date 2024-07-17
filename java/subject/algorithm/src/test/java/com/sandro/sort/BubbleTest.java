package com.sandro.sort;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BubbleTest {

    @Test
    void test() throws Exception {
        Bubble bubble = new Bubble(5, 2, 3, 4, 1);

        bubble.sort();

        assertThat(bubble).isEqualTo(new Bubble(1, 2, 3, 4, 5));
        assertThat(bubble.getLoopTime()).isEqualTo(5);
    }

    @DisplayName("이미 정렬되어 있다면 더 이상 루프를 돌지 않는다.")
    @Test
    void test2() throws Exception {
        Bubble bubble = new Bubble(1, 2, 3, 4, 5);

        bubble.sort();

        assertThat(bubble).isEqualTo(new Bubble(1, 2, 3, 4, 5));
        assertThat(bubble.getLoopTime()).isEqualTo(1);
    }

    @Test
    void test3() throws Exception {
        Bubble bubble = new Bubble(1, 2, 3, 5, 4);

        bubble.sort();

        assertThat(bubble).isEqualTo(new Bubble(1, 2, 3, 4, 5));
        assertThat(bubble.getLoopTime()).isEqualTo(2);
    }

}
package com.sandro.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SelectionTest {

    @Test
    void test() throws Exception {
        Selection selection = new Selection(3, 2, 4, 5, 1);

        selection.sort();

        assertThat(selection).isEqualTo(new Selection(1, 2, 3, 4, 5));
    }

}
package com.sandro.search;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BinarySearchTest {

    @Test
    void binarySearch() throws Exception {

        BinarySearch binarySearch = new BinarySearch(4, 1, 5, 2, 3);

        int[] result = binarySearch.search(1, 3, 7, 9, 5);

        assertThat(result).isEqualTo(new int[]{1, 1, 0, 0, 1});
    }

}
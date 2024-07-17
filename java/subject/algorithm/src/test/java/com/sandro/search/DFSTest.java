package com.sandro.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DFSTest {

    @Test
    void dfs() throws Exception {
        // Given
        int[][] graph = {
                null,
                new int[]{2, 3},
                new int[]{5, 6},
                new int[]{4},
                new int[]{6},
                new int[]{},
                new int[]{}
        };

        DFS dfs = new DFS(graph);

        // When
        List<Integer> result = dfs.run();

        // Then
        assertThat(result).isEqualTo(List.of(1, 2, 5, 6, 3, 4));
    }

    @DisplayName("주어진 그래프의 연결 요소의 갯수를 출력한다.")
    @Test
    void test() throws Exception {
        // Given
        int[][] graph = {
                null,
                new int[]{2, 5},
                new int[]{1, 5},
                new int[]{4},
                new int[]{3, 6},
                new int[]{2, 1},
                new int[]{4}
        };

        DFS dfs = new DFS(graph);

        // When
        int result = dfs.findHowManyGraphs();

        // Then
        assertThat(result).isEqualTo(2);
    }

    @DisplayName("주어진 그래프의 연결 요소의 갯수를 출력한다.")
    @Test
    void test2() throws Exception {
        // Given
        int[][] graph = {
                null,
                new int[]{2, 5},        // 1
                new int[]{1, 5, 4, 3},  // 2
                new int[]{4, 2},        // 3
                new int[]{3, 6, 5, 2},  // 4
                new int[]{2, 1, 4},     // 5
                new int[]{4}            // 6
        };

        DFS dfs = new DFS(graph);

        // When
        int result = dfs.findHowManyGraphs();

        // Then
        assertThat(result).isEqualTo(1);
    }

}
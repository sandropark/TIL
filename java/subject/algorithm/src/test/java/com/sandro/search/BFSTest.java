package com.sandro.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BFSTest {

    @Test
    void bfs() throws Exception {
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

        BFS bfs = new BFS(graph);

        // When
        List<Integer> result = bfs.run();

        // Then
        assertThat(result).isEqualTo(List.of(1, 2, 3, 5, 6, 4));
    }

    @DisplayName("BFS를 이용해서 목적지에 이르는 가장 얕은 깊이를 찾는다.")
    @Test
    void maze() throws Exception {
        // Given
        int[][] graph = {
                new int[]{1, 0, 1, 1, 1, 1},
                new int[]{1, 0, 1, 0, 1, 0},
                new int[]{1, 0, 1, 0, 1, 1},
                new int[]{1, 1, 1, 0, 1, 1}
        };

        Maze maze = new Maze(graph);

        // When
        int result = maze.run();

        // Then
        assertThat(result).isEqualTo(15);
    }

}
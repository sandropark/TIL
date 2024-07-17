package com.sandro.graph;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GraphTest {

    @DisplayName("이분 그래프 판별하기 (백준 1707)")
    @Nested
    class Bj1707 {

        @Test
        void case1() throws Exception {
            int numOfNode = 3;
            Graph graph = new Graph(numOfNode);

            graph.setEdge(1, 3);
            graph.setEdge(2, 3);

            graph.print();

            assertThat(graph.isBipartite()).isTrue();
        }

        @Test
        void case2() throws Exception {
            int numOfNode = 4;
            Graph graph = new Graph(numOfNode);

            graph.setEdge(1, 2);
            graph.setEdge(2, 3);
            graph.setEdge(3, 4);
            graph.setEdge(4, 2);

            graph.print();

            assertThat(graph.isBipartite()).isFalse();
        }
    }
}
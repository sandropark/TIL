package com.sandro.graph.shortestPathAlgorithm;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BellmanFordTest {

    BellmanFord.Node a = new BellmanFord.Node("a");
    BellmanFord.Node b = new BellmanFord.Node("b");
    BellmanFord.Node c = new BellmanFord.Node("c");
    BellmanFord.Node d = new BellmanFord.Node("d");
    BellmanFord.Node e = new BellmanFord.Node("e");
    BellmanFord.Node f = new BellmanFord.Node("f");
    BellmanFord.Node g = new BellmanFord.Node("g");
    BellmanFord.Node h = new BellmanFord.Node("h");
    BellmanFord.Node i = new BellmanFord.Node("i");

    @Test
    void test() throws Exception {
        // Given
        initBellmanFord();

        BellmanFord bellmanFord = new BellmanFord(a);

        // When & Then
        assertThat(bellmanFord.getShortestDistanceTo(i)).isEqualTo(12);
    }

    @Test
    void test1() throws Exception {
        // Given
        initBellmanFord1();

        BellmanFord bellmanFord = new BellmanFord(a);

        // When & Then
        assertThat(bellmanFord.getShortestDistanceTo(a)).isEqualTo(0);
        assertThat(bellmanFord.getShortestDistanceTo(c)).isEqualTo(1);
        assertThat(bellmanFord.getShortestDistanceTo(b)).isEqualTo(4);
    }

    @Test
    void test2() throws Exception {
        // Given
        initBellmanFord2();

        BellmanFord bellmanFord = new BellmanFord(a);

        // When & Then
        assertThat(bellmanFord.getShortestDistanceTo(a)).isEqualTo(0);
        assertThat(bellmanFord.getShortestDistanceTo(c)).isEqualTo(1);
        assertThat(bellmanFord.getShortestDistanceTo(b)).isEqualTo(4);
        assertThat(bellmanFord.getShortestDistanceTo(d)).isEqualTo(8);
    }

    private void initBellmanFord() {
        a.addEdge(b, 5);
        a.addEdge(c, 1);

        b.addEdge(d, 4);
        b.addEdge(f, 1);

        c.addEdge(b, 3);

        d.addEdge(c, 3);
        d.addEdge(e, 9);

        d.addEdge(f, 1);

        e.addEdge(c, 2);
        e.addEdge(h, 5);

        f.addEdge(e, 7);
        f.addEdge(g, 3);

        g.addEdge(e, 1);
        g.addEdge(h, 3);
        g.addEdge(i, 4);

        h.addEdge(g, 2);
        h.addEdge(i, 1);
    }

    private void initBellmanFord1() {
        a.addEdge(b, 5);
        a.addEdge(c, 1);

        c.addEdge(b, 3);
    }

    private void initBellmanFord2() {
        a.addEdge(b, 5);
        a.addEdge(c, 1);

        b.addEdge(d, 4);

        c.addEdge(b, 3);

        d.addEdge(c, 1);
    }
}





















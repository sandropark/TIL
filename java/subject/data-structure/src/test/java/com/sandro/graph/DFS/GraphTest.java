package com.sandro.graph.DFS;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GraphTest {

    @Test
    void dfs() throws Exception {
        Graph.DfsNode<String> a = new Graph.DfsNode<>("a");
        Graph.DfsNode<String> b = new Graph.DfsNode<>("b");
        Graph.DfsNode<String> c = new Graph.DfsNode<>("c");
        Graph.DfsNode<String> d = new Graph.DfsNode<>("d");

        a.addNeighbors(b, d);
        b.addNeighbors(c);

        Graph<String> graph = new Graph<>(a, b, c, d);

        List<String> dfs = graph.dfs();

        assertThat(dfs).isEqualTo(List.of("a", "b", "c", "d"));
    }

    @Test
    void dfs2() throws Exception {
        Graph.DfsNode<String> a = new Graph.DfsNode<>("a");
        Graph.DfsNode<String> b = new Graph.DfsNode<>("b");
        Graph.DfsNode<String> c = new Graph.DfsNode<>("c");
        Graph.DfsNode<String> d = new Graph.DfsNode<>("d");
        Graph.DfsNode<String> e = new Graph.DfsNode<>("e");
        Graph.DfsNode<String> f = new Graph.DfsNode<>("f");
        Graph.DfsNode<String> g = new Graph.DfsNode<>("g");
        Graph.DfsNode<String> h = new Graph.DfsNode<>("h");

        a.addNeighbors(b, c);
        b.addNeighbors(a, c, e, h);
        c.addNeighbors(a, b, d);
        d.addNeighbors(c, f);
        e.addNeighbors(b, g);
        f.addNeighbors(d);
        g.addNeighbors(e);
        h.addNeighbors(b);

        Graph<String> graph = new Graph<>(a, b, c, d, e, f, g, h);

        List<String> dfs = graph.dfs();

        assertThat(dfs).isEqualTo(List.of("a", "b", "c", "d", "f", "e", "g", "h"));

    }

}
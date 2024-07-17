package com.sandro.search;

import java.util.ArrayList;
import java.util.List;

public class DFS {

    private final int[][] graph;
    private final boolean[] visited;
    private final List<Integer> result = new ArrayList<>();

    public DFS(int[][] graph) {
        this.graph = graph;
        visited = new boolean[graph.length];
    }

    /**
     * @return result
     */
    public List<Integer> run() {
        dfs(1);
        return result;
    }

    private void dfs(int node) {
        visited[node] = true;
        result.add(node);
        for (int neighbor : graph[node]) {
            if (!visited[neighbor])
                dfs(neighbor);
        }
    }

    public int findHowManyGraphs() {
        int result = 0;
        for (int i = 1; i < graph.length; i++) {
            if (!visited[i]) {
                result++;
                dfs(i);
            }
        }
        return result;
    }
}

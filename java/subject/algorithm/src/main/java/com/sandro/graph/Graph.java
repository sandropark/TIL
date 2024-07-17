package com.sandro.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    private final List<Integer>[] list;
    private final Integer[] visited;
    private int grop;
    private boolean result = true;

    public Graph(int n) {
        list = new ArrayList[n];
        visited = new Integer[n];
        for (int i = 0; i < n; i++)
            list[i] = new ArrayList<>();
    }

    public void setEdge(int from, int to) {
        list[from - 1].add(to - 1);
    }

    public boolean isBipartite() {
        for (int i = 0; i < list.length; i++) {
            if (result)
                dfs(i);
            else
                break;
        }
        return true;
    }

    private void dfs(int node) {
        setGroup(node);
        for (int neighbor : list[node]) {
            if (notVisited(neighbor))
                dfs(neighbor);
            else if (bothAreSameGroup(node, neighbor))
                result = false;
        }
    }

    private void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        setGroup(0);

        while (!queue.isEmpty()) {
            Integer now = queue.poll();

            for (Integer next : list[now]) {
                if (notVisited(next)) {
                    setGroup(next);
                    queue.add(next);
                    continue;
                }
                if (bothAreSameGroup(now, next)) {
                    result = false;
                    return;
                }
            }
        }
    }

    private void setGroup(Integer node) {
        visited[node] = grop++;
    }

    private boolean notVisited(Integer next) {
        return visited[next] == null;
    }

    private boolean bothAreSameGroup(Integer now, Integer next) {
        return visited[now] % 2 == visited[next] % 2;
    }

    public void print() {
        System.out.println("=========================");
        for (List<Integer> integers : list)
            System.out.println(integers);
    }

}

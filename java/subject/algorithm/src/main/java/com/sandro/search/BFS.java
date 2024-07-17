package com.sandro.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    private final int[][] graph;
    private final boolean[] visited;
    private final List<Integer> result = new ArrayList<>();

    public BFS(int[][] graph) {
        this.graph = graph;
        visited = new boolean[graph.length];
    }

    public List<Integer> run() {
        visited[1] = true;
        result.add(1);
        bfs(1);
        return result;
    }

    private void bfs(int node) {
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                result.add(neighbor);
            }
        }
        for (int neighbor : graph[node])
            bfs(neighbor);
    }
}

class Maze {
    private final int[][] graph;
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};
    private final int n;
    private final int m;
    private int result;

    public Maze(int[][] graph) {
        this.graph = graph;
        n = graph.length;
        m = graph[0].length;
    }

    public int run() {
        bfs();
        return result;
    }

    private void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            if (isTarget(now[0], now[1])) break;

            for (int i = 0; i < 4; i++) {
                int nextX = now[0] + dx[i];
                int nextY = now[1] + dy[i];

                if (isNotValid(nextX, nextY)) continue;

                graph[nextX][nextY] += graph[now[0]][now[1]];
                queue.add(new int[]{nextX, nextY});
            }
        }
    }

    private boolean isTarget(int x, int y) {
        if (x == n - 1 && y == m - 1) {
            result = graph[x][y];
            return true;
        }
        return false;
    }

    private boolean isNotValid(int x, int y) {
        return isNotSafeIndex(x, y) || graph[x][y] != 1;
    }

    private boolean isNotSafeIndex(int x, int y) {
        return x < 0 || n <= x || y < 0 || m <= y;
    }
}
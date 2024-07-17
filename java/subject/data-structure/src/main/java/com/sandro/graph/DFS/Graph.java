package com.sandro.graph.DFS;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Graph<T> {

    private final List<DfsNode<T>> dfsNodes = new ArrayList<>();
    @Getter
    private DfsNode<T> root;
    private static int currentTime = 1;

    public Graph(DfsNode<T>... dfsNodes) {
        this.dfsNodes.addAll(Arrays.stream(dfsNodes).collect(Collectors.toList()));
        if (dfsNodes.length != 0)
            root = dfsNodes[0];
    }

    public List<String> dfs() {
        if (root == null)
            throw new IllegalStateException("노드가 없습니다.");
        ArrayList<String> result = new ArrayList<>();
        root.dfs(result);
        return result;
    }

    static class DfsNode<T> {
        private final List<DfsNode<T>> neighbors = new ArrayList<>();
        @Getter
        private final T key;
        private int preTime;
        private int postTime;
        @Setter
        private DfsNode<T> parent;
        private final List<DfsNode<T>> children = new ArrayList<>();

        public DfsNode(T key) {
            this.key = key;
        }

        public void addNeighbors(DfsNode<T>... neighbors) {
            this.neighbors.addAll(Arrays.stream(neighbors).collect(Collectors.toList()));
        }

        public void dfs(List<String> result) {
            result.add(key.toString());
            preTime = currentTime++;
            System.out.println("key = " + key + " , preTime = " + preTime);
            for (DfsNode<T> neighbor : neighbors) {
                if (neighbor.hasNotMet(result)) {
                    neighbor.dfs(result);
                    neighbor.setParent(this);
                    children.add(neighbor);
                }
            }
            postTime = currentTime++;
            System.out.println("key = " + key + " , postTime = " + postTime);
        }

        private boolean hasNotMet(List<String> result) {
            return !result.contains(key.toString());
        }
    }

}























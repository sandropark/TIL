package com.sandro.graph.shortestPathAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class BellmanFord {

    private final Node source;
    private final HashMap<Node, Integer> distanceMap;

    public BellmanFord(Node source) {
        this.source = source;
        distanceMap = findShortestPath();
    }

    public HashMap<Node, Integer> findShortestPath() {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(source, 0);
        source.updateMap(distanceMap);
        return distanceMap;
    }

    public int getShortestDistanceTo(Node destination) {
        return distanceMap.get(destination);
    }

    protected static class Node {
        private String value;
        private final List<Edge> edges = new ArrayList<>();

        public Node(String value) {
            this.value = value;
        }

        public void addEdge(Node to, Integer weight) {
            edges.add(new Edge(this, to, weight));
        }

        public void updateMap(HashMap<Node, Integer> distanceMap) {
            for (Edge edge : edges) {
                Node to = edge.to;
                Integer tempDistance = distanceMap.get(to);

                tempDistance = tempDistance == null ? Integer.MAX_VALUE : tempDistance;
                int newDistance = distanceMap.get(edge.from) + edge.weight;
                if (newDistance < tempDistance) {
                    distanceMap.put(to, newDistance);
                    to.updateMap(distanceMap);
                }
            }
        }

        @Override
        public String toString() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    private static class Edge {
        private Node from;
        private Node to;
        private Integer weight;

        public Edge(Node from, Node to, Integer weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
}

















package com.sandro.graph.tree.heap;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Heap {
    private final List<Integer> list;

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Node {
        private int value;
        private int index;

        public static Node of(int value, int index) {
            return new Node(value, index);
        }

        public boolean smallerThan(Node target) {
            return value < target.getValue();
        }
    }

    public static Heap of(int... values) {
        return new Heap(Arrays.stream(values).boxed().collect(Collectors.toList()));
    }

    public boolean isHeap() {
        int lastIndexOfList = list.size() - 1;
        for (int i = lastIndexOfList; i >= 0; i--)
            if (hasGreaterChild(i))
                return false;
        return true;
    }

    public void makeHeap() {
        for (int i = list.size() - 1; i >= 0; i--)
            heapifyDown(i);
    }

    public void insert(int value) {
        list.add(value);
        int lastIndex = list.size() - 1;
        heapifyUp(lastIndex);
    }

    public int findMax() {
        return list.get(0);
    }

    public void deleteMax() {
        moveLastToFirst();
        heapifyDown(0);
    }

    private boolean hasGreaterChild(int i) {
        Integer tempValue = list.get(i);
        if (hasRight(i)) {
            Integer left = list.get(i * 2 + 1);
            Integer right = list.get(i * 2 + 2);
            return tempValue < left || tempValue < right;
        }
        if (hasLeft(i)) {
            Integer left = list.get(i * 2 + 1);
            return tempValue < left;
        }
        return false;
    }

    private void heapifyDown(int i) {
        if (hasLeft(i)) {
            Node tempNode = getTempNode(i);
            Node leftNode = getLeftNode(i);
            Node maxChild = leftNode;
            if (hasRight(i))
                maxChild = max(leftNode, getRightNode(i));
            if (tempNode.smallerThan(maxChild)) {
                swap(tempNode, maxChild);
                heapifyDown(maxChild.getIndex());
            }
        }
    }

    private boolean hasRight(int i) {
        int rightIndex = i * 2 + 2;
        int lastIndexOfList = list.size() - 1;
        return rightIndex <= lastIndexOfList;
    }

    private boolean hasLeft(int i) {
        int leftIndex = i * 2 + 1;
        int lastIndexOfList = list.size() - 1;
        return leftIndex <= lastIndexOfList;
    }

    private Node getTempNode(int i) {
        return Node.of(list.get(i), i);
    }

    private Node getLeftNode(int i) {
        int leftIndex = i * 2 + 1;
        return Node.of(list.get(leftIndex), leftIndex);
    }

    private Node getRightNode(int i) {
        int rightIndex = i * 2 + 2;
        return Node.of(list.get(rightIndex), rightIndex);
    }

    private Node max(Node node1, Node node2) {
        return node1.getValue() > node2.getValue()
                ? node1
                : node2;
    }

    private void swap(Node tempNode, Node target) {
        list.set(tempNode.getIndex(), target.getValue());
        list.set(target.getIndex(), tempNode.getValue());
    }

    private void heapifyUp(int index) {
        if (hasParent(index)) {
            Node tempNode = getTempNode(index);
            Node parent = getParent(index);
            if (parent.smallerThan(tempNode)) {
                swap(tempNode, parent);
                heapifyUp(parent.getIndex());
            }
        }
    }

    private Node getParent(int i) {
        int parentIndex = (i - 1) / 2;
        return Node.of(list.get(parentIndex), parentIndex);
    }

    private boolean hasParent(int i) {
        int parentIndex = (i - 1) / 2;
        return parentIndex >= 0;
    }

    private void moveLastToFirst() {
        Integer last = list.get(list.size() - 1);
        list.set(0, last);
        list.remove(list.size() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Heap heap = (Heap) o;
        return Objects.equals(list, heap.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}

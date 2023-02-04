package list.linkedlist.implementation;

public class LinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private Object value;
        private Node next;
        public Node(Object input, Node next) {
            value = input;
            this.next = next;
        }
    }
    public int length() {
        return size;
    }

    public void addFirst(Object input) {
        head = new Node(input, head);
        size++;
        if (head.next == null) {
            tail = head;
        }
    }

    public Object get(int idx) {
        Node tmpNode = head;
        for (int i = 0; i < idx; i++) {
            tmpNode = tmpNode.next;
        }
        return tmpNode.value;
    }

    public void addLast(Object input) {
        Node lastNode = new Node(input, null);
        if (size == 0) {
            addFirst(input);
        } else {
            tail.next = lastNode;
            tail = lastNode;
            size++;
        }
    }

    private Node node(int idx) {
        Node tmpNode = head;
        for (int i = 0; i < idx; i++) {
            tmpNode = tmpNode.next;
        }
        return tmpNode;
    }

    public void add(int idx, Object input) {
        // 처음
        if (idx == 0) {
            addFirst(input);
            return;
        }
        // 마지막
        if (idx == size) {
            addLast(input);
            return;
        }

        Node preNode = node(idx - 1);
        Node tmpNode = preNode.next;
        preNode.next = new Node(input, tmpNode);
        size++;
    }

}

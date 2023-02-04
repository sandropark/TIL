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

}

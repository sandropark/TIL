package list.linkedlist.implementation;

public class LinkedList {

    private Node head;
    private Node tail;
    private int size;

    private static class Node {
        private final Object value;
        private Node next;
        public Node(Object input, Node next) {
            value = input;
            this.next = next;
        }
    }

    public class ListIterator {
        private Node next;

        public ListIterator() {
            this.next = head;
        }

        public Object next() {
            if (next == null) {
                throw new IllegalStateException("더 이상 값이 없습니다.");
            }
            Node result = next;
            next = next.next;
            return result.value;
        }

        public boolean hasNext() {
            return next != null;
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

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder().append("[");

        Node tmpNode = head;
        while (tmpNode.next != null) {
            sb.append(tmpNode.value).append(",");
            tmpNode = tmpNode.next;
        }

        return sb.append(tmpNode.value)
                .append("]")
                .toString();
    }

    public Object removeFirst() {
        Node removed = head;
        head = removed.next;
        size--;
        return removed.value;
    }

    public Object removeLast() {
        Node removed = tail;

        tail = node(size - 2);
        tail.next = null;
        size--;
        return removed.value;
    }

    public Object remove(int idx) {
        // 처음
        if (idx == 0) {
            return removeFirst();
        }

        // 마지막
        if (idx == size - 1) {
            return removeLast();
        }

        Node preNode = node(idx - 1);
        Node targetNode = preNode.next;
        preNode.next = preNode.next.next;
        size--;
        return targetNode.value;
    }

    public Object get(int idx) {
        return node(idx).value;
    }

    public int indexOf(Object input) {
        Node tmp = head;
        for (int i = 0; i < size; i++) {
            if (tmp.value.equals(input)) {
                return i;
            }
            tmp = tmp.next;
        }
        throw new IllegalArgumentException("유효하지 않은 값입니다. input=" + input);
    }

    public ListIterator iterator() {
        return new ListIterator();
    }

}

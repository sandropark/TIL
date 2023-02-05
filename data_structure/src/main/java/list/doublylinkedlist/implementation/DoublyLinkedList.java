package list.doublylinkedlist.implementation;

public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    public class Node {
        private final Object value;
        private Node next;
        private Node prev;
        public Node(Object input, Node next) {
            value = input;
            this.next = next;
        }

        public Node(Object input) {
            value = input;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public boolean isNextNull() {
            return next == null;
        }

        public boolean isPresentNext() {
            return next != null;
        }

        public Object getValue() {
            return value;
        }
    }

    public class ListIterator {
        private Node next;
        private Node lastReturned;
        private int nextIndex;

        public ListIterator() {
            this.next = head;
        }

        public Object next() {
            if (next == null) {
                throw new IllegalStateException("더 이상 값이 없습니다.");
            }
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.value;
        }

        public boolean hasNext() {
            return next != null;
        }

        public void add(Object input) {
            Node newNode = new Node(input, next);
            // 노드 중간이라면
            if (lastReturned == null) {
                head = newNode;
            } else {
                lastReturned.next = newNode;
            }
            next = newNode;
            size++;
        }

        public Object remove() {
            if (lastReturned == null) {
                throw new IllegalStateException("삭제할 노드가 없습니다.");
            }
            return DoublyLinkedList.this.remove(1 - nextIndex--);
        }
    }

    public int length() {
        return size;
    }

    public void addFirst(Object input) {
        Node newNode = new Node(input, head);
        if (head != null) {
            head.setPrev(newNode);
        }
        head = newNode;
        size++;
        if (head.isNextNull()) {
            tail = head;
        }
    }

    public void addLast(Object input) {
        Node newNode = new Node(input, null);
        if (size == 0) {
            addFirst(input);
        } else {
            link(tail, newNode);
            tail = newNode;
            size++;
        }
    }

    protected Node node(int idx) {
        Node tmpNode;

        if (idx < size / 2) {
            tmpNode = head;
            for (int i = 0; i < idx; i++) {
                tmpNode = tmpNode.next;
            }
        } else {
            tmpNode = tail;
            for (int i = size - 1; i > idx; i--) {
                tmpNode = tmpNode.prev;
            }
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

        Node prev = node(idx - 1);
        Node next = prev.next;
        Node newNode = new Node(input);

        link(prev, newNode);
        link(newNode, next);
        size++;
    }

    private void link(Node prev, Node next) {
        prev.setNext(next);
        next.setPrev(prev);
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
        if (size == 0) {
            throw new IllegalStateException("Node가 없습니다.");
        }

        Node toBeDeleted = head;
        if (toBeDeleted.isPresentNext()) {
            head = toBeDeleted.next;
            head.setPrev(null);
        }
        size--;
        if (size == 0) {
            head = null;
            tail = null;
        }
        return toBeDeleted.value;
    }

    public Object removeLast() {
        if (size == 0) {
            throw new IllegalStateException("Node가 없습니다.");
        }

        if (size == 1) {
            return removeFirst();
        }

        Node toBeDeleted = tail;
        tail = toBeDeleted.prev;
        tail.next = null;
        size--;
        return toBeDeleted.value;
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

        Node target = node(idx);
        Node prev = target.prev;
        Node next = target.next;
        link(prev, next);
        size--;
        return target.value;
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

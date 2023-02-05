package list.arraylist.implementation;

public class ArrayList {
    public static final int INITIAL_SIZE = 100;
    private int size;
    private final Object[] elementData = new Object[INITIAL_SIZE];

    public int length() {
        return size;
    }

    public boolean addLast(Object element) {
        elementData[size++] = element;
        return true;
    }

    public Object get(int idx) {
        return elementData[idx];
    }

    public void add(int idx, Object element) {
        // 1. 해당 인덱스의 요소부터 뒤의 요소들의 인덱스를 1씩 증가시킨다.
        for (int i = size-1; i >= idx; i--) {
            elementData[i + 1] = elementData[i];
        }
        // 2. 해당 인덱스에 입력받은 요소를 넣는다.
        elementData[idx] = element;
        size++;
    }

    public void addFirst(Object element) {
        add(0, element);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]);
            if (i < size - 1) {
                sb.append(",");
            }
        }
        return sb.append("]").toString();
    }

    public Object remove(int idx) {
        Object removed = elementData[idx];
        for (int i = idx; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
        return removed;
    }

    public Object removeFirst() {
        return remove(0);
    }

    public Object removeLast() {
        return remove(size-1);
    }

    public int indexOf(Object element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(element)) {
                return i;
            }
        }
        throw new IllegalArgumentException(element + "는 없는 요소입니다.");
    }

    public ListIterator listIterator() {
        return new ListIterator();
    }

    public class ListIterator {
        private int idx;

        public boolean hasNext() {
            return idx < size;
        }

        public Object next() {
            return elementData[idx++];
        }

        public boolean hasPrevious() {
            return idx > 0;
        }

        public Object previous() {
            return elementData[--idx];
        }

        public void add(Object element) {
            ArrayList.this.add(idx, element);
        }

        public void remove() {
            if (idx == 0) {
                throw new IllegalStateException("현재 요소가 없습니다.");
            }
            ArrayList.this.remove(--idx);
        }
    }

}

package list.arraylist.implement;

public class ArrayList {
    public static final int INITIAL_SIZE = 100;
    private int size;
    private Object[] elementData = new Object[INITIAL_SIZE];

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
        // 해당 인덱스가 마지막이라면
        if (idx == size) {
            elementData[idx] = element;
            size++;
            return;
        }

        // 해당 인덱스가 요소 사이라면
        // 1. 해당 인덱스의 요소부터 뒤의 요소들의 인덱스를 1씩 증가시킨다.
        for (int i = size-1; i >= idx; i--) {
            elementData[i + 1] = elementData[i];
        }
        // 2. 해당 인덱스에 입력받은 요소를 넣는다.
        elementData[idx] = element;
        size++;
    }

}

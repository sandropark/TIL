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

}

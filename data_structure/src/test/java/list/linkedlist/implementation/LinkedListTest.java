package list.linkedlist.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LinkedListTest {

    LinkedList numbers;

    @BeforeEach
    void setUp() {
        numbers = new LinkedList();
    }

    @DisplayName("리스트 요소의 갯수를 반환한다.")
    @Test
    void length() throws Exception {
        assertThat(numbers.length()).isEqualTo(0);
    }

    @DisplayName("리스트 가장 앞에 요소를 추가한다.")
    @Test
    void addFirst() throws Exception {
        numbers.addFirst(30);
        numbers.addFirst(20);
        numbers.addFirst(10);

        assertThat(numbers.length()).isEqualTo(3);
    }

    @DisplayName("리스트 가장 마지막에 요소를 추가한다.")
    @Test
    void addLast() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);

        assertThat(numbers.length()).isEqualTo(3);
        assertThat(numbers.toString()).isEqualTo("[10,20,30]");
    }

    @DisplayName("해당 인덱스에 노드를 추가한다. : 처음")
    @Test
    void add() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);

        numbers.add(0, 1);

        assertThat(numbers.length()).isEqualTo(3);
        assertThat(numbers.toString()).isEqualTo("[1,10,20]");
    }

    @DisplayName("해당 인덱스에 노드를 추가한다. : 중간")
    @Test
    void add2() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);

        numbers.add(1, 15);

        assertThat(numbers.length()).isEqualTo(3);
        assertThat(numbers.toString()).isEqualTo("[10,15,20]");
    }

    @DisplayName("해당 인덱스에 노드를 추가한다. : 마지막")
    @Test
    void add3() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);

        numbers.add(2, 30);

        assertThat(numbers.length()).isEqualTo(3);
        assertThat(numbers.toString()).isEqualTo("[10,20,30]");
    }

    @ParameterizedTest
    @CsvSource(value = {"0 []", "1 [10]", "2 [10,20]"}, delimiterString = " ")
    void toStringTest(int size, String expected) throws Exception {
        for (int i = 0; i < size; i++) {
            numbers.addLast((i+1) * 10);
        }
        assertThat(numbers.toString()).isEqualTo(expected);
    }

    @DisplayName("첫 번째 노드를 삭제한다.")
    @Test
    void removeFirst() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);

        Object removed = numbers.removeFirst();

        assertThat(removed).isEqualTo(10);
        assertThat(numbers.length()).isEqualTo(2);
        assertThat(numbers.toString()).isEqualTo("[20,30]");
    }

    @DisplayName("마지막 노드를 삭제한다.")
    @Test
    void removeLast() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);

        Object removed = numbers.removeLast();

        assertThat(removed).isEqualTo(30);
        assertThat(numbers.length()).isEqualTo(2);
        assertThat(numbers.toString()).isEqualTo("[10,20]");
    }

    @DisplayName("해당 인덱스의 노드를 삭제한다.")
    @ParameterizedTest
    @CsvSource(value = {"0-10-[20,30]", "1-20-[10,30]", "2-30-[10,20]"}, delimiterString = "-")
    void remove(int idx, int expectedRemoved, String expected) throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);

        Object removed = numbers.remove(idx);

        assertThat(removed).isEqualTo(expectedRemoved);
        assertThat(numbers.length()).isEqualTo(2);
        assertThat(numbers.toString()).isEqualTo(expected);
    }

    @DisplayName("해당 인덱스 위치 노드의 value를 반환한다.")
    @Test
    void get() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);

        assertThat(numbers.get(0)).isEqualTo(10);
        assertThat(numbers.get(1)).isEqualTo(20);
        assertThat(numbers.get(2)).isEqualTo(30);
    }

    @DisplayName("해당 데이터를 가진 노드의 인덱스를 반환한다.")
    @Test
    void indexOf() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);

        assertThat(numbers.indexOf(10)).isEqualTo(0);
        assertThat(numbers.indexOf(20)).isEqualTo(1);
        assertThat(numbers.indexOf(30)).isEqualTo(2);
    }

    @DisplayName("iterator 객체로 리스트를 순서대로 순회한다. 더 이상 값이 없다면 예외를 발생한다.")
    @Test
    void iterator_next() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);

        LinkedList.ListIterator i = numbers.iterator();

        assertThat(i.next()).isEqualTo(10);
        assertThat(i.next()).isEqualTo(20);
        assertThat(i.next()).isEqualTo(30);
        assertThatThrownBy(i::next).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("iterator 객체에 다음 노드가 있다면 true를, 없다면 false를 반환한다.")
    @Test
    void iterator_hasNext_true() throws Exception {
        numbers.addLast(10);

        LinkedList.ListIterator i = numbers.iterator();

        assertThat(i.hasNext()).isTrue();
        assertThat(i.next()).isEqualTo(10);
        assertThat(i.hasNext()).isFalse();
    }

    @Test
    void iterator_while() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);

        LinkedList.ListIterator i = numbers.iterator();

        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }

}
package list.arraylist.implement;

import list.arraylist.implement.ArrayList.ListIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArrayListTest {

    ArrayList numbers;

    @BeforeEach
    void setUp() {
        numbers = new ArrayList();
    }
    @DisplayName("리스트에 포함된 요소갯수를 반환한다.")
    @Test
    void length() throws Exception {
        assertThat(numbers.length()).isEqualTo(0);
    }

    @DisplayName("리스트의 마지막 인덱스에 요소를 추가한다.")
    @Test
    void addLast() throws Exception {
        numbers.addLast(10);

        assertThat(numbers.length()).isEqualTo(1);
    }

    @DisplayName("해당 인덱스의 요소를 반환한다.")
    @Test
    void get() throws Exception {
        numbers.addLast(10);

        assertThat(numbers.get(0)).isEqualTo(10);
    }

    @DisplayName("원하는 인덱스에 값 넣기 : 가장 마지막에 요소 추가")
    @Test
    void add() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);

        numbers.add(3, 40);

        assertThat(numbers.length()).isEqualTo(4);
        assertThat(numbers.get(3)).isEqualTo(40);
    }

    @DisplayName("원하는 인덱스에 값 넣기 : 요소들 사이에 값을 넣는다.")
    @Test
    void add2() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);

        numbers.add(1, 40);

        assertThat(numbers.length()).isEqualTo(4);

        assertThat(numbers.get(0)).isEqualTo(10);
        assertThat(numbers.get(1)).isEqualTo(40);
        assertThat(numbers.get(2)).isEqualTo(20);
        assertThat(numbers.get(3)).isEqualTo(30);
    }

    @DisplayName("리스트의 첫번째 인덱스에 요소를 추가한다.")
    @Test
    void addFirst() throws Exception {
        numbers.addLast(20);
        numbers.addLast(30);

        numbers.addFirst(10);

        assertThat(numbers.length()).isEqualTo(3);

        assertThat(numbers.get(0)).isEqualTo(10);
        assertThat(numbers.get(1)).isEqualTo(20);
        assertThat(numbers.get(2)).isEqualTo(30);
    }

    @Test
    void toStringTest() throws Exception {
        numbers.addFirst(10);
        numbers.addLast(20);

        assertThat(numbers.toString()).isEqualTo("[10,20]");
    }

    @DisplayName("해당하는 인덱스의 요소를 삭제하면서 반환한다. 빈 공간은 채워진다.")
    @Test
    void remove() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);
        numbers.addLast(40);

        Object removed = numbers.remove(1);

        assertThat(removed).isEqualTo(20);

        assertThat(numbers.length()).isEqualTo(3);

        assertThat(numbers.get(0)).isEqualTo(10);
        assertThat(numbers.get(1)).isEqualTo(30);
        assertThat(numbers.get(2)).isEqualTo(40);
    }

    @DisplayName("첫 번째 요소를 삭제한다.")
    @Test
    void removeFirst() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);
        numbers.addLast(40);

        Object removed = numbers.removeFirst();

        assertThat(removed).isEqualTo(10);

        assertThat(numbers.length()).isEqualTo(3);

        assertThat(numbers.get(0)).isEqualTo(20);
        assertThat(numbers.get(1)).isEqualTo(30);
        assertThat(numbers.get(2)).isEqualTo(40);
    }

    @DisplayName("마지막 요소를 삭제한다.")
    @Test
    void removeLast() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);
        numbers.addLast(40);

        Object removed = numbers.removeLast();

        assertThat(removed).isEqualTo(40);

        assertThat(numbers.length()).isEqualTo(3);

        assertThat(numbers.get(0)).isEqualTo(10);
        assertThat(numbers.get(1)).isEqualTo(20);
        assertThat(numbers.get(2)).isEqualTo(30);
    }

    @DisplayName("해당 요소의 인덱스를 반환한다.")
    @Test
    void indexOf() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);
        numbers.addLast(40);

        assertThat(numbers.indexOf(20)).isEqualTo(1);
    }

    @DisplayName("리스트에 없는 요소인 경우 예외가 발생한다.")
    @Test
    void indexOf_Exception() throws Exception {
        assertThatThrownBy(() -> numbers.indexOf(1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("호출할 때마다 처음 요소부터 반환한다.")
    @Test
    void listIterator_next() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);
        numbers.addLast(40);

        ListIterator li = numbers.listIterator();

        assertThat(li.next()).isEqualTo(10);
        assertThat(li.next()).isEqualTo(20);
        assertThat(li.next()).isEqualTo(30);
        assertThat(li.next()).isEqualTo(40);
    }

    @DisplayName("인덱스에 요소가 없다면 false를 반한다.")
    @Test
    void listIterator_hasNext_false() throws Exception {
        ListIterator li = numbers.listIterator();

        assertThat(li.hasNext()).isFalse();
    }

    @DisplayName("인덱스에 요소가 있다면 true를 반한다.")
    @Test
    void listIterator_hasNext_true() throws Exception {
        numbers.addLast(10);

        ListIterator li = numbers.listIterator();

        assertThat(li.hasNext()).isTrue();
    }

    @DisplayName("반복문 테스트 - next")
    @Test
    void listIterator_while_next() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);
        numbers.addLast(40);

        ListIterator li = numbers.listIterator();

        while (li.hasNext()) {
            System.out.println(li.next());
        }
    }

    @DisplayName("이전 요소가 없는 경우 false를 반환한다.")
    @Test
    void listIterator_hasPrevious_false() throws Exception {
        ListIterator li = numbers.listIterator();

        assertThat(li.hasPrevious()).isFalse();
    }

    @DisplayName("이전 요소가 있는 경우 true를 반환한다.")
    @Test
    void listIterator_hasPrevious_true() throws Exception {
        numbers.addLast(10);

        ListIterator li = numbers.listIterator();
        li.next();

        assertThat(li.hasPrevious()).isTrue();
    }

    @DisplayName("호출할 때마다 이전요소를 반환한다.")
    @Test
    void listIterator_previous() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);
        numbers.addLast(40);

        ListIterator li = numbers.listIterator();

        while (li.hasNext()) {
            li.next();
        }

        assertThat(li.previous()).isEqualTo(40);
        assertThat(li.previous()).isEqualTo(30);
        assertThat(li.previous()).isEqualTo(20);
        assertThat(li.previous()).isEqualTo(10);
    }

    @DisplayName("이전 요소가 없는 경우 예외가 발생한다.")
    @Test
    void listIterator_previous_exception() throws Exception {
        ListIterator li = numbers.listIterator();

        assertThatThrownBy(() -> li.previous())
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("반복문 테스트 - previous")
    @Test
    void listIterator_while_previous() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);
        numbers.addLast(40);

        ListIterator li = numbers.listIterator();

        while (li.hasNext()) {
            li.next();
        }

        while (li.hasPrevious()) {
            System.out.println(li.previous());
        }
    }

    @DisplayName("iterator 에 요소를 추가한다.")
    @Test
    void listIterator_add() throws Exception {
        ListIterator li = numbers.listIterator();

        li.add(10);

        assertThat(numbers.length()).isEqualTo(1);
        assertThat(li.next()).isEqualTo(10);
    }

    @DisplayName("iterator 에 요소를 삭제한다.")
    @Test
    void listIterator_remove() throws Exception {
        ListIterator li = numbers.listIterator();

        li.add(10);
        li.next();
        li.remove();

        assertThat(numbers.length()).isEqualTo(0);
        assertThat(li.hasNext()).isFalse();
    }

    @DisplayName("iterator 에 요소를 삭제한다. : 요소가 없는 경우 예외가 발생한다.")
    @Test
    void listIterator_remove_noElement() throws Exception {
        ListIterator li = numbers.listIterator();

        assertThatThrownBy(li::remove)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("iterator 에 요소를 삭제한다. : 바라보는 요소가 없는 경우 예외가 발생한다.")
    @Test
    void listIterator_remove_() throws Exception {
        numbers.addLast(10);

        ListIterator li = numbers.listIterator();

        assertThatThrownBy(li::remove)
                .isInstanceOf(IllegalStateException.class);
    }

}
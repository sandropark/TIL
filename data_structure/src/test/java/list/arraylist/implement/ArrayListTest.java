package list.arraylist.implement;

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

}
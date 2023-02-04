package list.linkedlist.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("해당 인덱스의 요소를 반환한다.")
    @Test
    void get() throws Exception {
        numbers.addFirst(30);
        numbers.addFirst(20);
        numbers.addFirst(10);

        assertThat(numbers.get(0)).isEqualTo(10);
        assertThat(numbers.get(1)).isEqualTo(20);
        assertThat(numbers.get(2)).isEqualTo(30);
    }

    @DisplayName("리스트 가장 마지막에 요소를 추가한다.")
    @Test
    void addLast() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);
        numbers.addLast(30);

        assertThat(numbers.length()).isEqualTo(3);
        assertThat(numbers.get(2)).isEqualTo(30);
    }

    @DisplayName("해당 인덱스에 노드를 추가한다. : 처음")
    @Test
    void add() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);

        numbers.add(0, 1);

        assertThat(numbers.length()).isEqualTo(3);

        assertThat(numbers.get(0)).isEqualTo(1);
        assertThat(numbers.get(1)).isEqualTo(10);
        assertThat(numbers.get(2)).isEqualTo(20);
    }

    @DisplayName("해당 인덱스에 노드를 추가한다. : 중간")
    @Test
    void add2() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);

        numbers.add(1, 15);

        assertThat(numbers.length()).isEqualTo(3);

        assertThat(numbers.get(0)).isEqualTo(10);
        assertThat(numbers.get(1)).isEqualTo(15);
        assertThat(numbers.get(2)).isEqualTo(20);
    }

    @DisplayName("해당 인덱스에 노드를 추가한다. : 마지막")
    @Test
    void add3() throws Exception {
        numbers.addLast(10);
        numbers.addLast(20);

        numbers.add(2, 30);

        assertThat(numbers.length()).isEqualTo(3);

        assertThat(numbers.get(0)).isEqualTo(10);
        assertThat(numbers.get(1)).isEqualTo(20);
        assertThat(numbers.get(2)).isEqualTo(30);
    }

}
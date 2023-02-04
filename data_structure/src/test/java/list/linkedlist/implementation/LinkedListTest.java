package list.linkedlist.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}
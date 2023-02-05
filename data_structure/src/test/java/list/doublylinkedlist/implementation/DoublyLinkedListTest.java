package list.doublylinkedlist.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DoublyLinkedListTest {

    DoublyLinkedList numbers;

    @BeforeEach
    void setUp() {
        numbers = new DoublyLinkedList();
    }

    @DisplayName("맨 앞에 노드를 추가한다.")
    @Test
    void addFirst() throws Exception {
        numbers.addFirst(20);
        assertThat(numbers.length()).isEqualTo(1);
        assertThat(numbers.get(0)).isEqualTo(20);

        numbers.addFirst(10);
        assertThat(numbers.length()).isEqualTo(2);
        assertThat(numbers.get(0)).isEqualTo(10);
        assertThat(numbers.get(1)).isEqualTo(20);
    }

    @DisplayName("맨 뒤에 노드를 추가한다.")
    @Test
    void addLast() throws Exception {
        numbers.addLast(10);
        assertThat(numbers.length()).isEqualTo(1);
        assertThat(numbers.get(0)).isEqualTo(10);

        numbers.addLast(20);
        assertThat(numbers.length()).isEqualTo(2);
        assertThat(numbers.get(0)).isEqualTo(10);
        assertThat(numbers.get(1)).isEqualTo(20);
    }

}
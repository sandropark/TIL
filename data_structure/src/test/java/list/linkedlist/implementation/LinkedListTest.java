package list.linkedlist.implementation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedListTest {

    @DisplayName("리스트 요소의 갯수를 반환한다.")
    @Test
    void length() throws Exception {
        LinkedList numbers = new LinkedList();

        assertThat(numbers.length()).isEqualTo(0);
    }

}
package list.arraylist.implement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

}
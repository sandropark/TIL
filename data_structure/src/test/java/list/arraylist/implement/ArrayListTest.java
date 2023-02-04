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
}
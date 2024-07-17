package com.sandro.triangle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TriangleTest {

    @DisplayName("숫자가 0보다 작다면 예외가 발생한다.")
    @Test
    void argumentsShouldBeGreaterThan0() throws Exception {
        assertThatThrownBy(() -> new Triangle(0, 0, 0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Triangle(-1, 1, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("한 변의 길이가 다른 두 변의 길이보다 작지 않다면 예외가 발생한다.")
    @Test
    void oneSideShouldBeSmallerThanSumOfTheOthers() throws Exception {
        assertThatThrownBy(() -> new Triangle(1, 1, 2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정삼각형이라면 1을 반환한다.")
    @Test
    void shouldReturn1() throws Exception {
        Triangle triangle = new Triangle(1, 1, 1);
        assertThat(triangle.code()).isEqualTo(1);
    }

    @DisplayName("부등변 삼각형이라면 3을 반환한다.")
    @Test
    void shouldReturn3() throws Exception {
        Triangle triangle = new Triangle(2, 3, 4);
        assertThat(triangle.code()).isEqualTo(3);
    }

    @DisplayName("이등변 삼각형이라면 2를 반환한다.")
    @Test
    void shouldReturn2() throws Exception {
        Triangle triangle = new Triangle(2, 2, 1);
        assertThat(triangle.code()).isEqualTo(2);
    }

}

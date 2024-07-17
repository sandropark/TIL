package com.sandro.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieTest {

    Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie();
    }

    @DisplayName("객체 생성 후 반환하는 평균값은 0이다.")
    @Test
    void given_rate_nothing_whenAndthen_return_0() throws Exception {
        // Given

        // When & Then
        assertThat(movie.averageRating()).isEqualTo(0);
    }

    @DisplayName("1을 넘기면 평균값으로 1을 반환한다.")
    @Test
    void given_rate_1_whenAndthen_return_1() throws Exception {
        // Given
        movie.rate(1);

        // When & Then
        assertThat(movie.averageRating()).isEqualTo(1);
    }

    @DisplayName("두 숫자를 넘기면 평균값을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,1,1","3,5,4"})
    void given_rate_two_movies_whenAndthen_return_average(int i1, int i2, int expected) throws Exception {
        // Given
        movie.rate(i1);
        movie.rate(i2);

        // When & Then
        assertThat(movie.averageRating()).isEqualTo(expected);
    }

}

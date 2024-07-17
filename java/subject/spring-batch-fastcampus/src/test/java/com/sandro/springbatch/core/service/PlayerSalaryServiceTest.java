package com.sandro.springbatch.core.service;

import com.sandro.springbatch.dto.PlayerDto;
import com.sandro.springbatch.dto.PlayerSalaryDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

class PlayerSalaryServiceTest {

    PlayerSalaryService playerSalaryService = new PlayerSalaryService();

    @DisplayName("선수의 나이로 연봉을 계산해서 반환한다.")
    @Test
    void calcSalary() throws Exception {
        // Given
        Year year = Year.of(2023);
        mockStatic(Year.class).when(Year::now).thenReturn(year);

        PlayerDto playerDto = PlayerDto.builder().birthYear(1980).build();

        // When
        PlayerSalaryDto playerSalaryDto = playerSalaryService.calcSalary(playerDto);

        // Then
        assertThat(playerSalaryDto.getSalary()).isEqualTo(43000000);
    }
}
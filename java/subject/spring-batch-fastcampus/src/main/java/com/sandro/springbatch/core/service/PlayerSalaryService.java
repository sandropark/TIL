package com.sandro.springbatch.core.service;

import com.sandro.springbatch.dto.PlayerDto;
import com.sandro.springbatch.dto.PlayerSalaryDto;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class PlayerSalaryService {

    public PlayerSalaryDto calcSalary(PlayerDto player) {
        int salary = (Year.now().getValue() - player.getBirthYear()) * 1000000;
        return player.toSalaryDto(salary);
    }
}

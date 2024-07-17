package com.sandro.springbatch.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerSalaryDto {
    private String ID;
    private String lastName;
    private String firstName;
    private String position;
    private int birthYear;
    private int debutYear;
    private int salary;
}

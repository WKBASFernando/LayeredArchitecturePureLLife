package com.assignment.purelifewaterbottles.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeAndSalaryDto {
    private String employeeId;
    private String salaryId;
    private String name;
    private String address;
    private String phoneNumber;
    private String position;
    private double salary;
}

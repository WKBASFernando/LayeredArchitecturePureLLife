package com.assignment.purelifewaterbottles.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeAndSalary {
    private String employeeId;
    private String salaryId;
    private String name;
    private String address;
    private String phoneNumber;
    private String position;
    private double salary;
}

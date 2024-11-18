package com.assignment.purelifewaterbottles.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeTm {
    private String employeeId;
    private String salaryId;
    private String name;
    private String address;
    private String phoneNumber;
    private String position;
    private double salary;
}

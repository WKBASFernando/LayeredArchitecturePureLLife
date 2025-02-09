package com.assignment.purelifewaterbottles.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DriverDto {
    private String driverId;
    private String vehicleId;
    private String name;
    private String phoneNo;
    private double driver_fee;
}

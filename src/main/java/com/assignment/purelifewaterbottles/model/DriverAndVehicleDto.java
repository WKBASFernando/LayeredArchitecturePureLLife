package com.assignment.purelifewaterbottles.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DriverAndVehicleDto {
    private String driverId;
    private String vehicleId;
    private String name;
    private String phoneNo;
    private String type;
    private String vehicleNumber;
    private double driver_fee;
}

package com.assignment.purelifewaterbottles.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DriverAndVehicle {
    private String driverId;
    private String vehicleId;
    private String name;
    private String phoneNo;
    private String type;
    private String vehicleNumber;
    private double driver_fee;
}

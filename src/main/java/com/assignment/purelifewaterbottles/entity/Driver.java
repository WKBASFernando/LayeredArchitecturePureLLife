package com.assignment.purelifewaterbottles.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Driver {
    private String driverId;
    private String vehicleId;
    private String name;
    private String phoneNo;
    private double driver_fee;
}

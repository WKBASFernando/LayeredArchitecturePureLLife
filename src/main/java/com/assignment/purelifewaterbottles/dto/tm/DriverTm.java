package com.assignment.purelifewaterbottles.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DriverTm {
    private String driverId;
    private String vehicleId;
    private String name;
    private int phoneNo;
    private double driver_fee;
}

package com.assignment.purelifewaterbottles.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DriverAndVehicleTm {
    private String driverId;
    private String vehicleId;
    private String name;
    private String phoneNo;
    private String type;
    private String vehicleNumber;
    private double driver_fee;
}

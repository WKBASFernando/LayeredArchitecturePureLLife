package com.assignment.purelifewaterbottles.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Delivery {
    private String deliveryId;
    private String driverId;
    private String location;
    private double delivery_fee;
}

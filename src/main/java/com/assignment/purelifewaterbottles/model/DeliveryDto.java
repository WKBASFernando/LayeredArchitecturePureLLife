package com.assignment.purelifewaterbottles.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeliveryDto {
    private String deliveryId;
    private String driverId;
    private String location;
    private double delivery_fee;
}

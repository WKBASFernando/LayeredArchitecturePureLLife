package com.assignment.purelifewaterbottles.view.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeliveryTm {
    private String deliveryId;
    private String driverId;
    private String location;
    private double delivery_fee;
}


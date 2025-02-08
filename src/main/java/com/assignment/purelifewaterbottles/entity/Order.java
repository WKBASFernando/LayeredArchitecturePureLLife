package com.assignment.purelifewaterbottles.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private String orderId;
    private String customerId;
    private String deliveryId;
    private String orderDate;
    private String description;
}

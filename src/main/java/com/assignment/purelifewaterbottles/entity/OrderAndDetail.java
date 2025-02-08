package com.assignment.purelifewaterbottles.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderAndDetail {
    private String orderId;
    private String customerId;
    private String deliveryId;
    private String itemId;
    private int item_qty;
    private String orderDate;
    private String description;
}

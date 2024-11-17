package com.assignment.purelifewaterbottles.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderAndDetailDto {
    private String orderId;
    private String customerId;
    private String deliveryId;
    private String itemId;
    private int item_qty;
    private String orderDate;
    private String description;
}

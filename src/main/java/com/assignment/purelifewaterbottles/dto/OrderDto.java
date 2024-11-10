package com.assignment.purelifewaterbottles.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    private String orderId;
    private String customerId;
    private String deliveryId;
    private String itemId;
    private int item_qty;
    private Date orderDate;
    private String description;

    public OrderDto(String orderId, String customerId, String deliveryId, Date orderDate, String description) {
    }

    public OrderDto(String orderId, String itemId, String item_qty) {
    }
}

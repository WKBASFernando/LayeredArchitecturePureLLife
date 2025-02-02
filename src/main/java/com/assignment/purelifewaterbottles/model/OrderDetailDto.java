package com.assignment.purelifewaterbottles.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailDto {
    private String orderId;
    private String itemId;
    private int item_qty;
}

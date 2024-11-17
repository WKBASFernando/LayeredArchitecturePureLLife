package com.assignment.purelifewaterbottles.dto.tm;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderTm {
    private String orderId;
    private String customerId;
    private String deliveryId;
    private String itemId;
    private int item_qty;
    private String orderDate;
    private String description;
}

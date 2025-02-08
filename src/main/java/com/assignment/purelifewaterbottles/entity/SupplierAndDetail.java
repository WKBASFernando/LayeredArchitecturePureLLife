package com.assignment.purelifewaterbottles.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierAndDetail {
    private String supplierId;
    private String supplingItem;
    private double pricePerOneItem;
    private int s_qty;
    private double totalPrice;
}

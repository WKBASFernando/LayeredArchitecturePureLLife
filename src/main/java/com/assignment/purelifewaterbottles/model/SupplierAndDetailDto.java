package com.assignment.purelifewaterbottles.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierAndDetailDto {
    private String supplierId;
    private String supplingItem;
    private double pricePerOneItem;
    private int s_qty;
    private double totalPrice;
}

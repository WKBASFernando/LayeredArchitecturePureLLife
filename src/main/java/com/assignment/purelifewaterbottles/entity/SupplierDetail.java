package com.assignment.purelifewaterbottles.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierDetail {
    private String supplierId;
    private String WarehouseId;
    private int s_qty;
    private double totalPrice;
}

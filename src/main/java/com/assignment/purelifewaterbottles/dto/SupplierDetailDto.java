package com.assignment.purelifewaterbottles.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierDetailDto {
    private String supplierId;
    private String WarehouseId;
    private int s_qty;
    private double totalPrice;
}

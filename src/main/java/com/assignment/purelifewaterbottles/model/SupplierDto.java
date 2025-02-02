package com.assignment.purelifewaterbottles.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierDto {
    private String supplierId;
    private String supplingItem;
    private double pricePerOneItem;
}

package com.assignment.purelifewaterbottles.view.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierTm {
    private String supplierId;
    private String supplingItem;
    private double pricePerOneItem;
    private int s_qty;
    private double totalPrice;
}

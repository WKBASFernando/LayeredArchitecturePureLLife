package com.assignment.purelifewaterbottles.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDetail {
    private String itemId;
    private String WarehouseId;
    private int itemQty;
}

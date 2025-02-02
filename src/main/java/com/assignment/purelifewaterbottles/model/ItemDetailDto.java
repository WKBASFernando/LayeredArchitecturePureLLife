package com.assignment.purelifewaterbottles.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDetailDto {
    private String itemId;
    private String WarehouseId;
    private int itemQty;
}

package com.assignment.purelifewaterbottles.dto;

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

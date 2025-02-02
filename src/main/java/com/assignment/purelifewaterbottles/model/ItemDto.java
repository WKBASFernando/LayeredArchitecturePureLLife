package com.assignment.purelifewaterbottles.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDto {
    private String itemId;
    private String name;
    private String capacity;
    private double price;
    private int itemQty;
}

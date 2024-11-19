package com.assignment.purelifewaterbottles.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemTm {
    private String itemId;
    private String name;
    private String capacity;
    private double price;
    private int itemQty;
}

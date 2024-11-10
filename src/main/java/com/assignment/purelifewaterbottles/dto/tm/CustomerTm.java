package com.assignment.purelifewaterbottles.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerTm {
    private String customerId;
    private String name;
    private String address;
    private String phone_no;
}
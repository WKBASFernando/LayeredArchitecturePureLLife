package com.assignment.purelifewaterbottles.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private String customerId;
    private String name;
    private String address;
    private String phone_no;
    private String email;
}

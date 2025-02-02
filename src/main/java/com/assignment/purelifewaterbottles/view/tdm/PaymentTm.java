package com.assignment.purelifewaterbottles.view.tdm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentTm {
    private String paymentId;
    private String orderId;
    private String pay_method;
}

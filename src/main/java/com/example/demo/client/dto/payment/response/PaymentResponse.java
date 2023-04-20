package com.example.demo.client.dto.payment.response;

import lombok.Data;

import java.io.Serializable;
@Data
public class PaymentResponse implements Serializable {
    private final RequestInfo requestInfo;
    private final PaymentInfo paymentInfo;
}

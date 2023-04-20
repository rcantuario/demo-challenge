package com.example.demo.client.dto.payment.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentInfo implements Serializable {
    private final double amount;
    private final String id;
}

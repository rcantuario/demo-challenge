package com.example.demo.client.dto.payment;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Account implements Serializable {
    private final String accountNumber;
    private final String currency;
    private final String routingNumber;
}

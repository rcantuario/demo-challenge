package com.example.demo.client.dto.payment;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class ExternalPaymentDto implements Serializable {
    private final Source source;
    private final Destination destination;
    private final double amount;

}

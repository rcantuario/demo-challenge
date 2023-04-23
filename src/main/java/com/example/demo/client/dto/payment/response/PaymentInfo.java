package com.example.demo.client.dto.payment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfo implements Serializable {
    private  double amount;
    private  String id;
}

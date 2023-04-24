package com.example.demo.chain;

import com.example.demo.client.dto.Payment;
import com.example.demo.client.dto.WalletTransactionResponse;
import com.example.demo.client.dto.payment.response.PaymentResponse;
import com.example.demo.model.Transaction;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class PaymentMessage {
    private Payment payment;
    private Transaction transaction;
    private PaymentResponse paymentResponse;
    private ResponseEntity<WalletTransactionResponse> walletTransactionResponse;
}

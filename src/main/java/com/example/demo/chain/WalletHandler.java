package com.example.demo.chain;

import com.example.demo.client.adapter.WalletAdapter;
import com.example.demo.client.dto.WalletTransaction;
import com.example.demo.client.dto.WalletTransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletHandler implements PaymentHandler{

    private PaymentHandler next;
    private final WalletAdapter walletAdapter;

    @Override
    public void setNext(PaymentHandler next) {
        this.next = next;
    }

    @Override
    public void handle(PaymentMessage message) {

        WalletTransaction request = new WalletTransaction();
        request.setAmount(message.getPayment().getAmount());
        request.setUser_id(String.valueOf(message.getPayment().getWalletId()));

        ResponseEntity<WalletTransactionResponse> walletTransaction = walletAdapter.createWalletTransaction(request);
        message.setWalletTransactionResponse(walletTransaction);

        next.handle(message);
    }
}

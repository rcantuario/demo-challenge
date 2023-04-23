package com.example.demo.chain;

import com.example.demo.client.adapter.WalletAdapter;
import com.example.demo.client.dto.Balance;
import com.example.demo.exception.InvalidBalanceException;
import com.example.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class BalanceHandler implements PaymentHandler{
    private final WalletAdapter walletAdapter;
    private  PaymentHandler next;
    private  TransactionRepository transactionRepository;

    @Override
    public void setNext(PaymentHandler next) {
        this.next = next;
    }

    @Override
    public void handle(PaymentMessage message) {
        Balance balance = walletAdapter.getBalance();
        if(message.getTransaction().getAmount() > balance.getAmount()) {
            throw new InvalidBalanceException("Invalid balance for account");
        } else {
            transactionRepository.save(message.getTransaction());
        }

        next.handle(message);
    }
}

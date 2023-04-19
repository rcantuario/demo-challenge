package com.example.demo.service;

import com.example.demo.client.adapter.WalletAdapter;
import com.example.demo.client.dto.Balance;
import com.example.demo.client.dto.Payment;
import com.example.demo.model.Transaction;
import com.example.demo.model.Transaction_Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.repository.TransactionRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;
    private final WalletAdapter walletAdapter;
    public void pay(Payment payment){
        Transaction t = new Transaction();
        t.setCurrency(payment.getCurrency());
        t.setAmount(payment.getAmount());
        t.setDate(LocalDate.now());
        t.setStatus(Transaction_Status.IN_PROGRESS.getDescription());

        Balance balance = walletAdapter.getBalance();

        if(t.getAmount() > balance.getAmount()){
            throw new IllegalArgumentException("invalid amount");
        } else {
            repository.save(t);

        }



    }
}

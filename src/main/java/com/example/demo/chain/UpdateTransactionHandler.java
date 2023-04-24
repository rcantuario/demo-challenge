package com.example.demo.chain;

import com.example.demo.client.dto.WalletTransactionResponse;
import com.example.demo.model.Transaction;
import com.example.demo.model.Transaction_Status;
import com.example.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateTransactionHandler implements PaymentHandler{

    private final TransactionRepository transactionRepository;
    PaymentHandler next;
    @Override
    public void setNext(PaymentHandler next) {
        this.next = next;
    }

    @Override
    public void handle(PaymentMessage message) {

        ResponseEntity<WalletTransactionResponse> response = message.getWalletTransactionResponse();

        if(response.getStatusCode().is2xxSuccessful()){
            Optional<Transaction> byId = transactionRepository.findById(message.getTransaction().getId());
            byId.get().setStatus(Transaction_Status.COMPLETED.getDescription());
            transactionRepository.save(byId.get());
        } else {
            Optional<Transaction> byId = transactionRepository.findById(message.getTransaction().getId());
            byId.get().setStatus(Transaction_Status.FAILED.getDescription());
            transactionRepository.save(byId.get());
        }


    }
}

package com.example.demo.service;

import com.example.demo.chain.PaymentHandler;
import com.example.demo.chain.PaymentHandlerFactory;
import com.example.demo.chain.PaymentMessage;
import com.example.demo.client.dto.Payment;
import com.example.demo.client.dto.payment.response.PaymentResponse;
import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final PaymentHandlerFactory handlerFactory;

    public PaymentResponse createTransaction(Payment payment){
        PaymentHandler handler = handlerFactory.getHandler();
        PaymentMessage message = new PaymentMessage();
        message.setPayment(payment);

        handler.handle(message);

        return message.getPaymentResponse();


    }

    public List<Transaction> getTransactions(double amount, LocalDate date){
        return transactionRepository.findByAmountAndDateOrderByDateDesc(amount, date);
    }
}

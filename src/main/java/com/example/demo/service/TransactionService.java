package com.example.demo.service;

import com.example.demo.chain.PaymentHandler;
import com.example.demo.chain.PaymentHandlerFactory;
import com.example.demo.chain.PaymentMessage;
import com.example.demo.client.adapter.PaymentsAdapter;
import com.example.demo.client.adapter.WalletAdapter;
import com.example.demo.client.dto.Balance;
import com.example.demo.client.dto.Payment;
import com.example.demo.client.dto.builder.ExternalPaymentBuilder;
import com.example.demo.client.dto.payment.ExternalPaymentDto;
import com.example.demo.client.dto.payment.response.PaymentResponse;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.InvalidBalanceException;
import com.example.demo.exception.WalletNotFoundException;
import com.example.demo.model.BankAccount;
import com.example.demo.model.Transaction;
import com.example.demo.model.Transaction_Status;
import com.example.demo.model.Wallet;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletAdapter walletAdapter;
    private final BankAccountRepository bankAccountRepository;
    private final PaymentsAdapter paymentsAdapter;
    private final WalletRepository walletRepository;
    private final ExternalPaymentBuilder externalPaymentBuilder;
    private final PaymentHandlerFactory handlerFactory;

    public PaymentResponse pay(Payment payment){
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

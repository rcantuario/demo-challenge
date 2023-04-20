package com.example.demo.service;

import com.example.demo.client.adapter.PaymentsAdapter;
import com.example.demo.client.adapter.WalletAdapter;
import com.example.demo.client.dto.Balance;
import com.example.demo.client.dto.Payment;
import com.example.demo.client.dto.payment.*;
import com.example.demo.model.BankAccount;
import com.example.demo.model.Transaction;
import com.example.demo.model.Transaction_Status;
import com.example.demo.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletAdapter walletAdapter;
    private final BankAccountRepository bankAccountRepository;
    private final PaymentsAdapter paymentsAdapter;

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
            transactionRepository.save(t);

            ExternalPaymentDto paymentDto = createPayment(payment);
            paymentsAdapter.createPayment(paymentDto);

        }
    }

    private ExternalPaymentDto createPayment(Payment payment) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(payment.getAccountId());

        SourceInformation sourceInformation = SourceInformation.builder()
                .name("ONTOP INC")
                .build();

        Account account = Account.builder().accountNumber(bankAccount.get().getAccountNumber())
                .routingNumber(bankAccount.get().getRoutingNumber())
                .currency(payment.getCurrency())
                .build();

        Source source = Source.builder().account(account)
                .sourceInformation(sourceInformation)
                .type("On top")
                .build();

        //TODO Replace by the correct account
        Destination destination = Destination.builder().name("TONY STARK").account(account).build();

        return ExternalPaymentDto.builder().source(source)
                .amount(payment.getAmount())
                .destination(destination)
                .build();
    }
}

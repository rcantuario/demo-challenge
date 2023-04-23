package com.example.demo.client.dto.builder;

import com.example.demo.client.dto.Payment;
import com.example.demo.client.dto.payment.*;
import com.example.demo.model.BankAccount;
import com.example.demo.model.Wallet;
import org.springframework.stereotype.Component;

@Component
public class ExternalPaymentBuilder {
    public ExternalPaymentDto build(Payment payment, BankAccount bankAccount, Wallet wallet){
        SourceInformation sourceInformation = SourceInformation.builder()
                .name("ONTOP INC")
                .build();

        Account account = Account.builder().accountNumber(bankAccount.getAccountNumber())
                .routingNumber(bankAccount.getRoutingNumber())
                .currency(payment.getCurrency())
                .build();

        Account accountSource = Account.builder().accountNumber(wallet.getAccountNumber())
                .routingNumber(wallet.getRoutingNumber())
                .currency(payment.getCurrency())
                .build();

        Source source = Source.builder().account(accountSource)
                .sourceInformation(sourceInformation)
                .type("COMPANY")
                .build();

        Destination destination = Destination.builder()
                .name(bankAccount.getFirstName() + bankAccount.getLastName())
                .account(account).build();

        return ExternalPaymentDto.builder().source(source)
                .amount(payment.getAmount())
                .destination(destination)
                .build();
    }
}

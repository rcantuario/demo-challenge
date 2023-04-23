package com.example.demo.chain;

import com.example.demo.client.adapter.PaymentsAdapter;
import com.example.demo.client.dto.Payment;
import com.example.demo.client.dto.builder.ExternalPaymentBuilder;
import com.example.demo.client.dto.payment.ExternalPaymentDto;
import com.example.demo.client.dto.payment.response.PaymentResponse;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.WalletNotFoundException;
import com.example.demo.model.BankAccount;
import com.example.demo.model.Wallet;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.repository.WalletRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreatePaymentHandler implements PaymentHandler{

    private PaymentHandler next;
    private final PaymentsAdapter paymentsAdapter;
    private final BankAccountRepository bankAccountRepository;
    private final WalletRepository walletRepository;
    private final ExternalPaymentBuilder externalPaymentBuilder;

    @Override
    public void setNext(PaymentHandler next) {
        this.next = next;
    }

    @Override
    public void handle(PaymentMessage message) {
            ExternalPaymentDto paymentDto = createPayment(message.getPayment());
            PaymentResponse paymentResponse = paymentsAdapter.createPayment(paymentDto);
            System.out.println(paymentResponse.getPaymentInfo().getId());
            message.setPaymentResponse(paymentResponse);
    }

    private ExternalPaymentDto createPayment(Payment payment) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(payment.getAccountId());
        Optional<Wallet> wallet = walletRepository.findById(payment.getWalletId());

        return externalPaymentBuilder.build(payment,
                bankAccount.orElseThrow(() -> new AccountNotFoundException(
                        String.format("Account %s not found ", payment.getAccountId())
                )),
                wallet.orElseThrow(() -> new WalletNotFoundException(
                        String.format("Wallet %s not found", payment.getWalletId()))));
    }
}

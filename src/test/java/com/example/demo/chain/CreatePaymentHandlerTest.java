package com.example.demo.chain;

import com.example.demo.client.adapter.PaymentsAdapter;
import com.example.demo.client.dto.Payment;
import com.example.demo.client.dto.builder.ExternalPaymentBuilder;
import com.example.demo.client.dto.payment.ExternalPaymentDto;
import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.WalletNotFoundException;
import com.example.demo.model.BankAccount;
import com.example.demo.model.Transaction;
import com.example.demo.model.Wallet;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatePaymentHandlerTest {
    @Mock
    private  PaymentsAdapter paymentsAdapter;
    @Mock
    private  BankAccountRepository bankAccountRepository;
    @Mock
    private  WalletRepository walletRepository;
    @Spy
    private  ExternalPaymentBuilder externalPaymentBuilder;
    @InjectMocks
    CreatePaymentHandler handler;

    @Test
    void testCreatePayment(){
        Payment payment = mock(Payment.class);
        payment.setAmount(1500);
        payment.setAccountId(10);
        Transaction transaction = new Transaction();
        transaction.setAmount(1500d);

        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setPayment(payment);
        paymentMessage.setTransaction(transaction);

        Mockito.when(bankAccountRepository.findById(anyInt()))
                .thenReturn(Optional.of(new BankAccount(1,"Tony","Stark","211927207","141259877","1885226711")));

        Mockito.when(walletRepository.findById(anyInt()))
                .thenReturn(Optional.of(new Wallet(10,"10","10","USD")));

        handler.handle(paymentMessage);
        verify(paymentsAdapter, times(1)).createPayment(any(ExternalPaymentDto.class));
    }

    @Test
    void testAccountNotFound(){
        Payment payment = mock(Payment.class);
        payment.setAmount(1500);
        payment.setAccountId(10);
        Transaction transaction = new Transaction();
        transaction.setAmount(1500d);

        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setPayment(payment);
        paymentMessage.setTransaction(transaction);

        Mockito.when(bankAccountRepository.findById(anyInt()))
                .thenReturn(Optional.ofNullable(null));

        assertThrows(AccountNotFoundException.class, () -> {
            handler.handle(paymentMessage);
        });
    }

    @Test
    void testWalletNotFound(){
        Payment payment = mock(Payment.class);
        payment.setAmount(1500);
        payment.setAccountId(10);
        Transaction transaction = new Transaction();
        transaction.setAmount(1500d);

        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setPayment(payment);
        paymentMessage.setTransaction(transaction);

        Mockito.when(bankAccountRepository.findById(anyInt()))
                .thenReturn(Optional.of(new BankAccount(1,"Tony","Stark","211927207",
                        "141259877","1885226711")));

        Mockito.when(walletRepository.findById(anyInt()))
                .thenReturn(Optional.ofNullable(null));

        assertThrows(WalletNotFoundException.class, () -> {
            handler.handle(paymentMessage);
        });
    }
}
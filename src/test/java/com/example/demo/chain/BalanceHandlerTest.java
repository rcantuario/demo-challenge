package com.example.demo.chain;

import com.example.demo.client.adapter.WalletAdapter;
import com.example.demo.client.dto.Balance;
import com.example.demo.client.dto.Payment;
import com.example.demo.exception.InvalidBalanceException;
import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BalanceHandlerTest {
    @Mock
    private WalletAdapter walletAdapter;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    CreatePaymentHandler createPaymentHandler;
    @InjectMocks
    BalanceHandler handler;

    Balance balance;

    @BeforeEach
    void setup(){
        handler.setNext(createPaymentHandler);
        balance = new Balance();
        balance.setUserId(123);
        balance.setAmount(2000);
    }

    @Test
    void testExceptionWhenBalanceSmallerThanPayment(){

        Payment payment = mock(Payment.class);
        payment.setAmount(500000);
        Transaction transaction = new Transaction();
        transaction.setAmount(500000d);

        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setPayment(payment);
        paymentMessage.setTransaction(transaction);

        Mockito.when(walletAdapter.getBalance()).thenReturn(this.balance);

        assertThrows(InvalidBalanceException.class, () -> {
            handler.handle(paymentMessage);
        });
    }

    @Test
    void testWhenBalanceIsGreaterThanPayment(){
        Payment payment = mock(Payment.class);
        payment.setAmount(1500);
        Transaction transaction = new Transaction();
        transaction.setAmount(1500d);

        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setPayment(payment);
        paymentMessage.setTransaction(transaction);

        Mockito.when(walletAdapter.getBalance()).thenReturn(this.balance);
        handler.handle(paymentMessage);
        verify(transactionRepository, times(1)).save(any(Transaction.class));


    }

}
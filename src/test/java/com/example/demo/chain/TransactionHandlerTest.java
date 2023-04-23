package com.example.demo.chain;

import com.example.demo.client.dto.Payment;
import com.example.demo.model.Transaction_Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TransactionHandlerTest {

    @Mock
    BalanceHandler balanceHandler;

    @InjectMocks
    TransactionHandler handler;

    @BeforeEach
    void setup(){
        handler.setNext(balanceHandler);
    }

    @Test
    void createTransactionMatchingPayment(){
        Payment payment = mock(Payment.class);
        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setPayment(payment);

        handler.handle(paymentMessage);

        assertEquals(payment.getAmount(),paymentMessage.getTransaction().getAmount());
        assertEquals(payment.getCurrency(),paymentMessage.getTransaction().getCurrency());
    }

    @Test
    void testTransactionDefaultStatus(){
        Payment payment = mock(Payment.class);
        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setPayment(payment);

        handler.handle(paymentMessage);

        assertEquals(Transaction_Status.IN_PROGRESS.getDescription(),
                paymentMessage.getTransaction().getStatus());
    }

}
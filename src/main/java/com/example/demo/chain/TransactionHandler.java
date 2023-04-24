package com.example.demo.chain;

import com.example.demo.client.dto.Payment;
import com.example.demo.model.Transaction;
import com.example.demo.model.Transaction_Status;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class TransactionHandler implements PaymentHandler{

    PaymentHandler next;
    @Override
    public void setNext(PaymentHandler next) {
        this.next = next;
    }

    @Override
    public void handle(PaymentMessage message) {
        Transaction transaction = createTransactionForPayment(message.getPayment());
        message.setTransaction(createTransactionForPayment(message.getPayment()));
        message.getPayment().setAmount(transaction.getAmountSent());

        next.handle(message);
    }

    private Transaction createTransactionForPayment(Payment payment) {
        Transaction t = new Transaction();
        t.setCurrency(payment.getCurrency());
        t.setAmount(payment.getAmount());
        t.setDate(LocalDate.now());
        t.setStatus(Transaction_Status.IN_PROGRESS.getDescription());
        t.setFee(t.getAmount() * 0.1);
        t.setAmountSent(t.getAmount() - t.getFee());
        return t;
    }
}

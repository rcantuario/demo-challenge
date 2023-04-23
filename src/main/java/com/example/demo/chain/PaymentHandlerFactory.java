package com.example.demo.chain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentHandlerFactory {
    private final TransactionHandler transactionHandler;
    private final BalanceHandler balanceHandler;
    private final CreatePaymentHandler createPaymentHandler;

    public PaymentHandler getHandler(){
        transactionHandler.setNext(balanceHandler);
        balanceHandler.setNext(createPaymentHandler);

        return transactionHandler;
    }

}

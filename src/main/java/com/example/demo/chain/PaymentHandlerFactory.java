package com.example.demo.chain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentHandlerFactory {
    private final TransactionHandler transactionHandler;
    private final BalanceHandler balanceHandler;
    private final WalletHandler walletHandler;
    private final CreatePaymentHandler createPaymentHandler;
    private final UpdateTransactionHandler updateTransactionHandler;

    public PaymentHandler getHandler(){
        transactionHandler.setNext(balanceHandler);
        balanceHandler.setNext(walletHandler);
        walletHandler.setNext(createPaymentHandler);
        createPaymentHandler.setNext(updateTransactionHandler);

        return transactionHandler;
    }

}

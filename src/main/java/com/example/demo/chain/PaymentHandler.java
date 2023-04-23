package com.example.demo.chain;

public interface PaymentHandler {
    void setNext (PaymentHandler next);
    void handle (PaymentMessage message);
}

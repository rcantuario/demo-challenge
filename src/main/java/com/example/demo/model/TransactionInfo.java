package com.example.demo.model;

import jakarta.persistence.Entity;

@Entity
public class TransactionInfo {
    private String currency;
    private double amountSent;
    private double amountReceived;
    private double fee;
    
}

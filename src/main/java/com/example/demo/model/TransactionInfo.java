package com.example.demo.model;

import javax.persistence.*;

@Entity
public class TransactionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String currency;
    private double amountSent;
    private double amountReceived;
    private double fee;
    @OneToOne
    private Transaction transaction;

}

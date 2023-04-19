package com.example.demo.model;


import javax.persistence.*;

import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String status;
    private String currency;
    private LocalDate date;
    private Double amount;



}

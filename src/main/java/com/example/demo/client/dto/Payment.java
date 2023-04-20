package com.example.demo.client.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class Payment implements Serializable {

    private int accountId;
    private int walletId;
    private double amount;
    private String currency;

}

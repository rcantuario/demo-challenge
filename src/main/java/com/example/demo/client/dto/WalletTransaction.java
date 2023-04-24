package com.example.demo.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WalletTransaction {
    @JsonProperty("amount")
    private double amount;

    @JsonProperty("user_id")
    private String user_id;
}

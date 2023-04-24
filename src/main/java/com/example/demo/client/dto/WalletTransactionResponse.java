package com.example.demo.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WalletTransactionResponse {
    @JsonProperty("wallet_transaction_id")
    private String walletTransactionId;
    @JsonProperty("user_id")
    private String userId;
    private double amount;
}

package com.example.demo.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class Balance implements Serializable {
    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("balance")
    private double amount;
}

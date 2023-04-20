package com.example.demo.client.dto.payment.response;

import lombok.Data;

import java.io.Serializable;
@Data
public class RequestInfo implements Serializable {
    private final String status;
}

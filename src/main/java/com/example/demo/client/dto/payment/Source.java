package com.example.demo.client.dto.payment;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class Source implements Serializable {
    private final String type;
    private final SourceInformation sourceInformation;
    private final Account account;
}

package com.example.demo.client.dto.payment;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class SourceInformation implements Serializable {
    private final String name;
}

package com.example.demo.client.dto.payment;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class SourceInformation implements Serializable {
    private final String name;
}

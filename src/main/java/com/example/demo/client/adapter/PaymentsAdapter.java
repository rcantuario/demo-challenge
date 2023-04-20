package com.example.demo.client.adapter;

import com.example.demo.client.dto.Balance;
import com.example.demo.client.dto.payment.ExternalPaymentDto;
import com.example.demo.client.dto.payment.response.PaymentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class PaymentsAdapter {

    @Value("${services.payments.url}")
    private String paymentUrl;
    public PaymentResponse createPayment(ExternalPaymentDto paymentDto){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PaymentResponse> response = restTemplate.
                postForEntity(URI.create(paymentUrl), paymentDto, PaymentResponse.class);

                System.out.println(response);

        return response.getBody();
    }
}

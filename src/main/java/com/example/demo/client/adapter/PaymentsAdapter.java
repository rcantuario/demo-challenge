package com.example.demo.client.adapter;

import com.example.demo.client.dto.Balance;
import com.example.demo.client.dto.payment.ExternalPaymentDto;
import com.example.demo.client.dto.payment.response.PaymentResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

        System.out.println(paymentDto);
        Gson gson = new Gson();
        String json = gson.toJson(paymentDto);
        System.out.println(json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(json,headers);

        ResponseEntity<String> response = restTemplate.
                postForEntity(URI.create(paymentUrl), request, String.class);

        System.out.println(response);

        return null;
    }
}

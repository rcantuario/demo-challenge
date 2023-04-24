package com.example.demo.client.adapter;

import com.example.demo.client.dto.WalletTransaction;
import com.example.demo.client.dto.WalletTransactionResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class WalletAdapter {

    @Value("${services.wallet.transactions.url}")
    private String walletUrl;

    public WalletTransactionResponse createWalletTransaction(WalletTransaction walletTransaction){
        RestTemplate restTemplate = new RestTemplate();

        System.out.println(walletTransaction);
        Gson gson = new Gson();
        String json = gson.toJson(walletTransaction);
        System.out.println(json);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(json,headers);

        ResponseEntity<WalletTransactionResponse> response = restTemplate.
                postForEntity(URI.create(walletUrl), request, WalletTransactionResponse.class);

        System.out.println(response.getBody());

        return response.getBody();
    }
}

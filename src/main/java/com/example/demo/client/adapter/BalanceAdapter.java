package com.example.demo.client.adapter;

import com.example.demo.client.dto.Balance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BalanceAdapter {
    @Value("${services.wallet.balance.url}")
    private String balanceUrl;

    public Balance getBalance(){
        RestTemplate restTemplate = new RestTemplate();
        Balance response = restTemplate.getForObject(balanceUrl, Balance.class);

        System.out.println(response);

        return response;
    }
}

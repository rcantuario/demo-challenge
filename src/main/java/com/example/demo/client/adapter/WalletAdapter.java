package com.example.demo.client.adapter;

import com.example.demo.client.dto.Balance;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WalletAdapter {

    public Balance getBalance(){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl  = "http://mockoon.tools.getontop.com:3000/wallets/balance?user_id=1000";
        Balance response = restTemplate.getForObject(fooResourceUrl, Balance.class);

        System.out.println(response);

        return response;
    }
}

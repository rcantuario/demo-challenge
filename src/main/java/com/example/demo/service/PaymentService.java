package com.example.demo.service;

import com.example.demo.client.dto.Balance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    public void getWalletBalance(){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl  = "http://mockoon.tools.getontop.com:3000/wallets/balance?user_id=1000";
        Balance response = restTemplate.getForObject(fooResourceUrl, Balance.class);

        System.out.println(response);

    }

}

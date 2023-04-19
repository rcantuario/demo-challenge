package com.example.demo.controller;

import com.example.demo.client.dto.Payment;
import com.example.demo.service.PaymentService;
import com.example.demo.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DemoController {
    private final PaymentService paymentService;
    private final TransactionService transactionService;

    @GetMapping("/hello")
    public String hello(){
        paymentService.getWalletBalance();

        return "Renan";

    }
    @PostMapping(value = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String pay(@RequestBody Payment payment){

        transactionService.pay(payment);

        return "Gremio";
    }
}

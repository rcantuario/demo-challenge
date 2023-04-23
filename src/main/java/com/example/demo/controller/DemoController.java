package com.example.demo.controller;

import com.example.demo.client.dto.Payment;
import com.example.demo.client.dto.payment.response.PaymentResponse;
import com.example.demo.model.Transaction;
import com.example.demo.service.PaymentService;
import com.example.demo.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class DemoController {
    private final PaymentService paymentService;
    private final TransactionService transactionService;

    @GetMapping("/hello")
    public String hello(){
        paymentService.getWalletBalance();

        return "Renan";

    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public PaymentResponse pay(@RequestBody Payment payment){

        return transactionService.pay(payment);

    }

    @GetMapping
    public List<Transaction> listTransactions(@RequestParam Double amount, @RequestParam LocalDate date){
        return transactionService.getTransactions(amount, date);
    }
}

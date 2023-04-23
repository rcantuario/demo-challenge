package com.example.demo.controller;

import com.example.demo.client.dto.Payment;
import com.example.demo.client.dto.payment.response.PaymentResponse;
import com.example.demo.model.Transaction;
import com.example.demo.service.PaymentService;
import com.example.demo.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class DemoController {
    private final PaymentService paymentService;
    private final TransactionService transactionService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public PaymentResponse createTransaction(@RequestBody Payment payment){

        try{
            return transactionService.createTransaction(payment);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
        }
    }

    @GetMapping
    public List<Transaction> listTransactions(@RequestParam Double amount, @RequestParam LocalDate date){
        return transactionService.getTransactions(amount, date);
    }

    @RequestMapping("/ping")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }
}

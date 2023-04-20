package com.example.demo.service;

import com.example.demo.model.BankAccount;
import com.example.demo.repository.BankAccountRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataStartup {
    private final PaymentService paymentService;
    private final TransactionService transactionService;
    private final BankAccountRepository bankAccountRepository;

    @PostConstruct
    public void init(){
        bankAccountRepository.save(new BankAccount(10,"Vinicius","JR","459036","141259877","4569871"));
        bankAccountRepository.save(new BankAccount(1,"Karim","Benzema","98375987","1234567","986578"));
    }


}

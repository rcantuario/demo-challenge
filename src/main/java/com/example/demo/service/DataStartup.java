package com.example.demo.service;

import com.example.demo.model.BankAccount;
import com.example.demo.model.Wallet;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.repository.WalletRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataStartup {
    private final PaymentService paymentService;
    private final TransactionService transactionService;
    private final BankAccountRepository bankAccountRepository;
    private final WalletRepository walletRepository;

    @PostConstruct
    public void init(){
        bankAccountRepository.save(new BankAccount(1,"Tony","Stark","211927207","141259877","1885226711"));
        //bankAccountRepository.save(new BankAccount(1,"Karim","Benzema","98375987","1234567","986578"));

        walletRepository.save(new Wallet(1,"0245253419","028444018","USD"));
    }


}

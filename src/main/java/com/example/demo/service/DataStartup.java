package com.example.demo.service;

import com.example.demo.model.BankAccount;
import com.example.demo.model.Transaction;
import com.example.demo.model.Wallet;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.WalletRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DataStartup {
    private final PaymentService paymentService;
    private final BankAccountRepository bankAccountRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @PostConstruct
    public void init(){
        bankAccountRepository.save(new BankAccount(1,"Tony","Stark","211927207","141259877","1885226711"));
        walletRepository.save(new Wallet(1,"0245253419","028444018","USD"));

        transactionRepository.save(new Transaction(10,"Completed","USD",
                LocalDate.parse("2022-08-16"),100d,90d,10d));

        transactionRepository.save(new Transaction(11,"in progress","USD",
                LocalDate.parse("2022-08-16"),
                -70d,63d,7d));

        transactionRepository.save(new Transaction(12,"in progress","USD",
                LocalDate.parse("2023-01-10"),
                10d,9d,1d));
    }


}

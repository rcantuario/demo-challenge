package com.example.demo.service;

import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    TransactionRepository repository;

    @InjectMocks
    TransactionService transactionService;

    @Test
    void getTransactions() {

        Transaction transaction1 = mock(Transaction.class);
        Transaction transaction2 = mock(Transaction.class);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction1);
        transactionList.add(transaction2);

        Mockito.when(repository.findByAmountAndDateOrderByDateDesc(Mockito.anyDouble(),Mockito.any(LocalDate.class)))
                .thenReturn(transactionList);

        List<Transaction> transactions = transactionService.getTransactions(15, LocalDate.now());
        assertEquals(2,transactions.size());

    }

//    @Test
//    void getTransactions() {
//    }
}
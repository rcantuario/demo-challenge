package com.example.demo.model;



import lombok.*;

import jakarta.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String routingNumber;
    private String nationalIdNumber;
    private String accountNumber;



}

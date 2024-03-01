package com.microservice.Account.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @Setter @ToString @SuperBuilder
@NoArgsConstructor
public class Accounts extends BaseEntity{
    @Id
    private Long accountNumber;

    private Long customerId;

    private String accountType;

    private String branchAddress;
}

package com.microservice.Account.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @Setter @ToString
@SuperBuilder @NoArgsConstructor
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue
    private Long customerId;

    private String name;

    private String email;

    private String mobileNumber;
}

package com.microservice.Cards.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public class Cards extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer cardId;

    private Integer totalAmount;

    private Integer amountUsed = 0;

    private Integer availableAmount;

    private String mobileNumber;

    private String cardNumber;

    private String cardType;
}

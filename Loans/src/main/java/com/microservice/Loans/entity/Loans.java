package com.microservice.Loans.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
public class Loans extends BaseEntity{

    @Id
    @GeneratedValue
    private Long loanId;

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private Integer totalLoan;

    private Integer amountPaid = 0;

    private Integer outstandingAmount;

}

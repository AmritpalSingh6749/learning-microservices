package com.microservice.Loans.mapper;

import com.microservice.Loans.dto.LoansDto;
import com.microservice.Loans.entity.Loans;

public class LoansMapper {
    private LoansMapper(){}
    public static Loans mapToLoan(LoansDto loansDto, Loans loans){
        loans.setLoanType(loansDto.getLoanType());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setAmountPaid(loansDto.getAmountPaid()+loans.getAmountPaid());
        loans.setOutstandingAmount(loans.getTotalLoan() - loans.getAmountPaid());
        return loans;
    }

    public static LoansDto mapToLoansDto(Loans loans){
        return LoansDto.builder()
                .loanType(loans.getLoanType())
                .mobileNumber(loans.getMobileNumber())
                .totalLoan(loans.getTotalLoan())
                .outstandingAmount(loans.getOutstandingAmount())
                .loanNumber(loans.getLoanNumber())
                .amountPaid(loans.getAmountPaid())
                .build();
    }
}

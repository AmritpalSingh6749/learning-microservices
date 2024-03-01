package com.microservice.Loans.service;

import com.microservice.Loans.dto.LoansDto;

import java.util.List;

public interface ILoansService {
    void createLoan(LoansDto loansDto);

    List<LoansDto> fetchLoan(String mobileNumber);

    void updateLoan(LoansDto loansDto);

    void deleteLoan(String loanNumber, String mobileNumber);
}

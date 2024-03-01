package com.microservice.Loans.service.impl;

import com.microservice.Loans.dto.LoansDto;
import com.microservice.Loans.entity.Loans;
import com.microservice.Loans.exceptions.ResourceNotFoundException;
import com.microservice.Loans.mapper.LoansMapper;
import com.microservice.Loans.repository.LoansRepository;
import com.microservice.Loans.service.ILoansService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {
    private LoansRepository loansRepository;

    @Override
    public void createLoan(LoansDto loansDto){
        Loans loan = LoansMapper.mapToLoan(loansDto, new Loans());
        String loanNumber = String.valueOf( (long)1e11 + new Random().nextLong((long)9e10));
        loan.setLoanNumber(loanNumber);
        loansRepository.save(loan);
    }

    @Override
    public List<LoansDto> fetchLoan(String mobileNumber) {
        List<Loans> loans = loansRepository.findAllByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
        return loans.stream().map(LoansMapper :: mapToLoansDto).toList();
    }

    @Override
    public void updateLoan(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "loanNumber", loansDto.getLoanNumber()));
        LoansMapper.mapToLoan(loansDto, loans);
        loansRepository.save(loans);
    }

    @Override
    public void deleteLoan(String loanNumber, String mobileNumber) {
        List<Loans> loans = loansRepository.findAllByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
        Loans loan = loans.stream().filter(l -> Objects.equals(l.getLoanNumber(), loanNumber)).toList().stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "loanNumber", loanNumber));
        loansRepository.delete(loan);
    }
}

package com.microservice.Account.service.client;

import com.microservice.Account.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoansFallback implements LoansFeignClient{
    @Override
    public ResponseEntity<List<LoansDto>> fetchLoan(String correlationId, String mobileNumber) {
        return null;
    }
}

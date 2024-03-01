package com.microservice.Account.service.client;

import com.microservice.Account.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="loans", fallback = LoansFallback.class)
public interface LoansFeignClient {

    @GetMapping("api/fetch")
    ResponseEntity<List<LoansDto>> fetchLoan(@RequestHeader("bank-correlation-id") String correlationId,
                                             @RequestParam String mobileNumber);
}

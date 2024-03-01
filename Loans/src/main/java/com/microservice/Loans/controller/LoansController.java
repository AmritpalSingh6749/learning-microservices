package com.microservice.Loans.controller;

import com.microservice.Loans.constants.LoansConstants;
import com.microservice.Loans.dto.LoansContactInfoDto;
import com.microservice.Loans.dto.LoansDto;
import com.microservice.Loans.dto.ResponseDto;
import com.microservice.Loans.service.ILoansService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
@EnableConfigurationProperties(LoansContactInfoDto.class)
public class LoansController {

    private static final Logger logger = LoggerFactory.getLogger(LoansController.class);

    private ILoansService loansService;
    private LoansContactInfoDto loansContactInfoDto;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createLoan(@Valid @RequestBody LoansDto loansDto) {
        loansService.createLoan(loansDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<List<LoansDto>> fetchLoan(@RequestHeader("bank-correlation-id") String correlationId,
                                                    @Pattern(regexp = "$|[0-9]{10}", message = "please provide a valid mobileNumber")
                                                    @RequestParam String mobileNumber) {
        logger.debug("bank-correlation-id found: {}", correlationId);
        List<LoansDto> loansDtos = loansService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDtos);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDto> updateLoan(@Valid @RequestBody LoansDto loansDto) {
        loansService.updateLoan(loansDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@Pattern(regexp = "$|[0-9]{12}", message = "Please provide a valid loanNumber")
                                                  @RequestParam String loanNumber,
                                                  @Pattern(regexp = "$|[0-9]{10}", message = "Please provide a valid mobileNumber")
                                                  @RequestParam String mobileNumber) {
        loansService.deleteLoan(loanNumber, mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
    }


    @RateLimiter(name="getContactInfo", fallbackMethod = "getContactInfoFallback")
    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDto> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(loansContactInfoDto);
    }

    public ResponseEntity<LoansContactInfoDto> getContactInfoFallback() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }
}

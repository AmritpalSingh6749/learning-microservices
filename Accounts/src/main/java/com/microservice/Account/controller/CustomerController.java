package com.microservice.Account.controller;

import com.microservice.Account.dto.CustomerDetailsDto;
import com.microservice.Account.service.ICustomerService;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
@AllArgsConstructor
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private ICustomerService customerService;

    @GetMapping("fetch-customer-details")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader(name = "bank-correlation-id") String correlationId,
                                                                   @Pattern(regexp = "$|[0-9]{10}", message = "Please provide valid mobile number")
                                                                   @RequestParam String mobileNumber) {
        logger.debug("bank-correlation-id found: {}", correlationId);
        CustomerDetailsDto customerDetailsDto = customerService.fetchCustomerDetails(mobileNumber, correlationId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDetailsDto);
    }
}

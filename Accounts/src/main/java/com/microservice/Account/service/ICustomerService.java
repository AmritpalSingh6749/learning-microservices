package com.microservice.Account.service;

import com.microservice.Account.dto.CustomerDetailsDto;

public interface ICustomerService {
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}

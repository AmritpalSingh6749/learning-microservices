package com.microservice.Account.service;

import com.microservice.Account.dto.CustomerDto;

public interface IAccountsService {
    void createAccount(CustomerDto dto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    void deleteAccount(String mobileNumber);
}

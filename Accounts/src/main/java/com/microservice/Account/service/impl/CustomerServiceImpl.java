package com.microservice.Account.service.impl;

import com.microservice.Account.dto.CardsDto;
import com.microservice.Account.dto.CustomerDetailsDto;
import com.microservice.Account.dto.LoansDto;
import com.microservice.Account.entity.Accounts;
import com.microservice.Account.entity.Customer;
import com.microservice.Account.exception.ResourceNotFoundException;
import com.microservice.Account.mapper.AccountsMapper;
import com.microservice.Account.mapper.CustomerMapper;
import com.microservice.Account.repository.AccountsRepository;
import com.microservice.Account.repository.CustomerRepository;
import com.microservice.Account.service.ICustomerService;
import com.microservice.Account.service.client.CardsFeignClient;
import com.microservice.Account.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()));

        ResponseEntity<List<LoansDto>> loansEntity = loansFeignClient.fetchLoan(correlationId, mobileNumber);

        ResponseEntity<List<CardsDto>> cardsEntity = cardsFeignClient.fetchCards(correlationId, mobileNumber);

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts));
        if(loansEntity != null)
            customerDetailsDto.setLoansDtoList(loansEntity.getBody());
        if(cardsEntity != null)
            customerDetailsDto.setCardsDtoList(cardsEntity.getBody());

        return customerDetailsDto;
    }
}
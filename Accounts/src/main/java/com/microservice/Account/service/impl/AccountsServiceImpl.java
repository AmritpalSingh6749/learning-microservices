package com.microservice.Account.service.impl;

import com.microservice.Account.constants.AccountsConstants;
import com.microservice.Account.dto.AccountsDto;
import com.microservice.Account.dto.AccountsMessageDto;
import com.microservice.Account.dto.CustomerDto;
import com.microservice.Account.entity.Accounts;
import com.microservice.Account.entity.Customer;
import com.microservice.Account.exception.CustomerAlreadyExistsException;
import com.microservice.Account.exception.ResourceNotFoundException;
import com.microservice.Account.mapper.AccountsMapper;
import com.microservice.Account.mapper.CustomerMapper;
import com.microservice.Account.repository.AccountsRepository;
import com.microservice.Account.repository.CustomerRepository;
import com.microservice.Account.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
    private static final Logger logger = LoggerFactory.getLogger(AccountsServiceImpl.class);
    private final StreamBridge streamBridge;
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        if (customerRepository.findByMobileNumber(customerDto.getMobileNumber()).isPresent())
            throw new CustomerAlreadyExistsException("Customer already exists with given mobile number " + customerDto.getMobileNumber());
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Customer savedCustomer = customerRepository.save(customer);
        Accounts savedAccount = accountsRepository.save(createNewAccount(savedCustomer));
        sendCommunication(savedAccount, savedCustomer);
    }

    private void sendCommunication(Accounts accounts, Customer customer) {
        AccountsMessageDto accountsMsgDto = new AccountsMessageDto(accounts.getAccountNumber(), customer.getName(),
                customer.getEmail(), customer.getMobileNumber());
        logger.info("Sending communication request for details: {}", accountsMsgDto.toString());
        boolean res = streamBridge.send("sendCommunication-out-0", accountsMsgDto);
        logger.info("Is the communication request successfully passed? {}", res);
    }

    private Accounts createNewAccount(Customer customer) {
        Long accountNumber = (long) 1e10 + new Random().nextInt((int) 9e8);
        return Accounts.builder()
                .accountType(AccountsConstants.SAVING)
                .branchAddress(AccountsConstants.ADDRESS)
                .accountNumber(accountNumber)
                .customerId(customer.getCustomerId())
                .build();
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer);
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Accounts", "accountNumber", accountsDto.getAccountNumber().toString()));
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
        }
        return true;
    }

    @Override
    public void deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.delete(customer);
    }

}

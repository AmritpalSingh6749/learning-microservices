package com.microservice.Account.mapper;

import com.microservice.Account.dto.AccountsDto;
import com.microservice.Account.entity.Accounts;

public class AccountsMapper {
    public static AccountsDto mapToAccountsDto(Accounts accounts){
        return AccountsDto.builder()
                .accountType(accounts.getAccountType())
                .branchAddress(accounts.getBranchAddress())
                .accountNumber(accounts.getAccountNumber())
                .build();
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts){
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        return accounts;
    }
}

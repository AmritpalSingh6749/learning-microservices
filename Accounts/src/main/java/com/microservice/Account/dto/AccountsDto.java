package com.microservice.Account.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountsDto {
    private Long accountNumber;
    private String branchAddress;
    private String accountType;
}

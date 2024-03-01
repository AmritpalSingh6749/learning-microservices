package com.microservice.Account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDetailsDto {
    @NotEmpty(message = "Name cannot be null or empty")
    private String name;

    @Email(message = "Email should be of format abc@xyz.com")
    @NotEmpty(message = "Email cannot be null or empty")
    private String email;

    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Pattern(regexp = "$|[0-9]{10}", message = "Please provide a valid mobile number")
    private String mobileNumber;

    private AccountsDto accountsDto;

    private List<LoansDto> loansDtoList;

    private List<CardsDto> cardsDtoList;
}
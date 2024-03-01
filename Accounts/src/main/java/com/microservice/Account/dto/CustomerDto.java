package com.microservice.Account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
    @NotEmpty(message = "name cannot be null or empty")
    private String name;

    @Email(message = "email should be of format abc@xyz.com")
    @NotEmpty(message = "Email cannot be null or empty")
    private String email;

    @NotEmpty(message = "mobileNumber cannot be null or empty")
    @Pattern(regexp = "$|[0-9]{10}", message = "Please provide a valid mobile number")
    private String mobileNumber;

    private AccountsDto accountsDto;
}

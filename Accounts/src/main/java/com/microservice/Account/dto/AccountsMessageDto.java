package com.microservice.Account.dto;

public record AccountsMessageDto(Long accountNumber, String name, String mobileNumber, String email) {
}

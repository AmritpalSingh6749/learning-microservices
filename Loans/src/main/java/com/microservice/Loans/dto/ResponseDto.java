package com.microservice.Loans.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDto {
    private String responseCode;
    private String responseMsg;
}

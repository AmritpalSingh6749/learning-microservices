package com.microservice.Cards.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ErrorResponseDto {
    private LocalDateTime errorAt;
    private String errorDescription;
    private HttpStatus errorCode;
    private String errorPath;
}

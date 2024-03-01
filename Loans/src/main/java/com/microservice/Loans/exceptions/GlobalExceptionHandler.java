package com.microservice.Loans.exceptions;

import com.microservice.Loans.dto.ErrorResponseDto;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest req) {
        String errors = getErrorsString(ex.getAllErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto.builder()
                        .apiPath(req.getDescription(false))
                        .errorMessage(errors)
                        .timeStamp(LocalDateTime.now())
                        .errorCode(HttpStatus.BAD_REQUEST)
                        .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> globalException(Exception ex, WebRequest req) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponseDto.builder()
                        .apiPath(req.getDescription(false))
                        .errorMessage(ex.getMessage())
                        .timeStamp(LocalDateTime.now())
                        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build()
                );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> resourceNotFound(Exception ex, WebRequest req) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto.builder()
                        .apiPath(req.getDescription(false))
                        .errorMessage(ex.getMessage())
                        .timeStamp(LocalDateTime.now())
                        .errorCode(HttpStatus.BAD_REQUEST)
                        .build()
                );
    }

    private String getErrorsString(List<ObjectError> allErrors) {
        return allErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining());
    }
}

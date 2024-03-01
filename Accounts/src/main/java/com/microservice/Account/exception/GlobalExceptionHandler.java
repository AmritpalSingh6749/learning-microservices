package com.microservice.Account.exception;

import com.microservice.Account.dto.ErrorResponseDto;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> globalException(Exception ex, WebRequest req) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponseDto.builder()
                        .errorTime(LocalDateTime.now())
                        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                        .apiPath(req.getDescription(false))
                        .errorMessage(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> customerAlreadyExistsException(Exception ex, WebRequest req) {

        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .apiPath(req.getDescription(false))
                        .errorMessage(ex.getMessage())
                        .errorCode(HttpStatus.BAD_REQUEST)
                        .errorTime(LocalDateTime.now())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> resourceNotFoundException(Exception ex, WebRequest req) {

        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .apiPath(req.getDescription(false))
                        .errorMessage(ex.getMessage())
                        .errorCode(HttpStatus.NOT_FOUND)
                        .errorTime(LocalDateTime.now())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        String errors = ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return new ResponseEntity<>(
                ErrorResponseDto.builder()
                        .apiPath(request.getDescription(false))
                        .errorCode(HttpStatus.BAD_REQUEST)
                        .errorMessage(errors)
                        .build(),
                HttpStatus.BAD_REQUEST);
    }
}

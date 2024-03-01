package com.microservice.Cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String entityName, String resourceName, String resourceValue) {
        super(String.format("%s not found for %s: %s", entityName, resourceName, resourceValue));
    }
}

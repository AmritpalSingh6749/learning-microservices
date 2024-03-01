package com.microservice.Loans.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue){
        super(String.format("%s entity not found for %s: %s", resourceName, fieldName, fieldValue));
    }
}

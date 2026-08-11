package com.micobank.account.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    // Constructor to create exception with resource details
    public ResourceNotFoundException(
            String resourceName,
            String fieldName,
            String fieldValue
    ) {

        // Create a meaningful error message
        super(String.format(
                "%s not found with %s: %s",
                resourceName,
                fieldName,
                fieldValue
        ));
    }
}
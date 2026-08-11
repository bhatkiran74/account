package com.micobank.account.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Customer already exists")
public class CustomerAlreadyExistException extends RuntimeException {

    // Constructor to pass a custom exception message
    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}
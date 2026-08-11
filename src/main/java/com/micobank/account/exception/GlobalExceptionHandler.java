package com.micobank.account.exception;

import com.micobank.account.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle exception when the requested resource is not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlerResourceNotFoundException(
            ResourceNotFoundException resourceNotFoundException,
            WebRequest webRequest
    ) {

        // Create error response with exception details
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                resourceNotFoundException.getMessage(),
                LocalDateTime.now()
        );

        // Return 404 NOT FOUND response
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    // Handle exception when customer already exists
    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handlerCustomerAlreadyExistException(
            CustomerAlreadyExistException customerAlreadyExistException,
            WebRequest webRequest
    ) {

        // Create error response with exception details
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                customerAlreadyExistException.getMessage(),
                LocalDateTime.now()
        );

        // Return 400 BAD REQUEST response
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    // Handle exception global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handlerGlobalException(
            Exception resourceNotFoundException,
            WebRequest webRequest
    ) {

        // Create error response with exception details
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                resourceNotFoundException.getMessage(),
                LocalDateTime.now()
        );

        // Return 500 NOT FOUND response
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

package com.micobank.account.rest;


import com.micobank.account.constants.AccountConstants;
import com.micobank.account.dto.CustomerDto;
import com.micobank.account.dto.ResponseDto;
import com.micobank.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/account", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountRestController {

    // Inject account service to handle account-related business logic
    @Autowired
    private IAccountService iAccountService;

    // Create a new customer account
    @PostMapping("/create")
    ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {

        // Call service to create the account
        iAccountService.createAccount(customerDto);

        // Return HTTP 201 response after successful account creation
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(
                        AccountConstants.STATUS_201,
                        AccountConstants.MESSAGE_201));
    }

    // Update existing customer account details
    @PutMapping("/update")
    ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto customerDto) {

        // Call service to update account details
        boolean isUpdated = iAccountService.updateAccount(customerDto);

        // Return success response if account was updated successfully
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(
                            AccountConstants.STATUS_200,
                            AccountConstants.MESSAGE_200));
        } else {

            // Return bad request response if update operation fails
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(
                            AccountConstants.STATUS_500,
                            AccountConstants.MESSAGE_500));
        }
    }

    // Fetch account details using the customer's mobile number
    @GetMapping("/fetch")
    ResponseEntity<CustomerDto> fetchAccountDetailsUsingMobileNo(
            @RequestParam String mobileNumber) {

        // Retrieve account details from the service layer
        CustomerDto customerDto = iAccountService.findAccountDetails(mobileNumber);

        // Return account details with HTTP 200 status
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    // Delete customer account using the mobile number
    @DeleteMapping("/delete")
    ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber) {

        // Call service to delete the account
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);

        // Return success response if account was deleted
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(
                            AccountConstants.STATUS_200,
                            AccountConstants.MESSAGE_200));
        } else {

            // Return bad request response if delete operation fails
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(
                            AccountConstants.STATUS_500,
                            AccountConstants.MESSAGE_500));
        }
    }

}
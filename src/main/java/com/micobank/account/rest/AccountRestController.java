package com.micobank.account.rest;


import com.micobank.account.constants.AccountConstants;
import com.micobank.account.dto.CustomerDto;
import com.micobank.account.dto.ResponseDto;
import com.micobank.account.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/account", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(name = "Account Management", description = "APIs for managing customer accounts - create, read, update, and delete operations")
public class AccountRestController {

    @Autowired
    private IAccountService iAccountService;

    @PostMapping("/create")
    @Operation(
            summary = "Create a new customer account",
            description = "Creates a new customer account with associated account details. " +
                    "The customer name, email, mobile number, and account information are required.",
            tags = {"Account Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Account created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input - validation failed",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Customer already exists with the given mobile number",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<ResponseDto> createAccount(
            @Valid @RequestBody CustomerDto customerDto
    ) {
        iAccountService.createAccount(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(
                        AccountConstants.STATUS_201,
                        AccountConstants.MESSAGE_201));
    }

    @PutMapping("/update")
    @Operation(
            summary = "Update existing customer account",
            description = "Updates the details of an existing customer account. " +
                    "The mobile number is used to identify the account to be updated.",
            tags = {"Account Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Account updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input or account update failed",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Customer account not found",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<ResponseDto> updateAccount(
            @Valid @RequestBody CustomerDto customerDto
    ) {
        boolean isUpdated = iAccountService.updateAccount(customerDto);

        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(
                            AccountConstants.STATUS_200,
                            AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(
                            AccountConstants.STATUS_500,
                            AccountConstants.MESSAGE_500));
        }
    }

    @GetMapping("/fetch")
    @Operation(
            summary = "Retrieve customer account details",
            description = "Fetches complete customer and account information using the mobile number. " +
                    "The mobile number must be a valid 10-digit Indian number starting with 6-9.",
            tags = {"Account Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Account details retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomerDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid mobile number format",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Customer not found with the provided mobile number",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<CustomerDto> fetchAccountDetailsUsingMobileNo(
            @Parameter(
                    name = "mobileNumber",
                    description = "10-digit Indian mobile number (format: [6-9]XXXXXXXXX)",
                    example = "9876543210",
                    required = true
            )
            @RequestParam
            @Pattern(regexp = "^[6-9]\\d{9}$", message = "Please provide a valid 10-digit Indian mobile number")
            String mobileNumber) {

        CustomerDto customerDto = iAccountService.findAccountDetails(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @DeleteMapping("/delete")
    @Operation(
            summary = "Delete customer account",
            description = "Deletes a customer account using the mobile number. " +
                    "Once deleted, the account and all associated data will be permanently removed.",
            tags = {"Account Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Account deleted successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid mobile number format or deletion failed",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Customer account not found",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<ResponseDto> deleteAccount(
            @Parameter(
                    name = "mobileNumber",
                    description = "10-digit Indian mobile number (format: [6-9]XXXXXXXXX)",
                    example = "9876543210",
                    required = true
            )
            @RequestParam
            @Pattern(regexp = "^[6-9]\\d{9}$", message = "Please provide a valid 10-digit Indian mobile number")
            String mobileNumber) {

        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(
                            AccountConstants.STATUS_200,
                            AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(
                            AccountConstants.STATUS_500,
                            AccountConstants.MESSAGE_500));
        }
    }

}
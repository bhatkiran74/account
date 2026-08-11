package com.micobank.account.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "Full name of the customer",
            example = "John Doe",
            minLength = 2,
            maxLength = 50
    )
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Schema(
            description = "Email address of the customer",
            example = "john.doe@example.com",
            format = "email"
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @Schema(
            description = "10-digit Indian mobile number starting with 6-9",
            example = "9876543210",
            pattern = "^[6-9]\\d{9}$"
    )
    @NotBlank(message = "Mobile number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Mobile number must be a valid 10-digit Indian mobile number"
    )
    private String mobileNumber;

    @Schema(
            description = "Account details associated with the customer"
    )
    @Valid
    private AccountDto accountDto;
}

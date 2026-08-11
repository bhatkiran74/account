package com.micobank.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold Account information"
)
public class AccountDto {

    @Schema(
            description = "Unique account number assigned by the bank",
            example = "1000000000001",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long accountNumber;

    @Schema(
            description = "Type of account (e.g., Savings, Current, Business)",
            example = "Savings",
            maxLength = 20
    )
    @NotBlank(message = "Account type is required")
    @Size(max = 20, message = "Account type must not exceed 20 characters")
    private String accountType;

    @Schema(
            description = "Physical address of the bank branch",
            example = "123 Main Street, New York, NY 10001",
            maxLength = 200
    )
    @NotBlank(message = "Branch address is required")
    @Size(max = 200, message = "Branch address must not exceed 200 characters")
    private String branchAddress;
}
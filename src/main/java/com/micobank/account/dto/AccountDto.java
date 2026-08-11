package com.micobank.account.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class AccountDto {

    // Account number should be provided for existing accounts
    private Long accountNumber;

    // Account type is required
    @NotBlank(message = "Account type is required")
    @Size(max = 20, message = "Account type must not exceed 20 characters")
    private String accountType;

    // Branch address is required
    @NotBlank(message = "Branch address is required")
    @Size(max = 200, message = "Branch address must not exceed 200 characters")
    private String branchAddress;
}
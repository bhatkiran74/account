package com.micobank.account.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CardDto {

    @NotBlank(message = "Mobile number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Mobile number must be a valid 10-digit Indian mobile number"
    )
    private String mobileNumber;

    @NotBlank(message = "Card number is required")
    @Size(min = 16, max = 16, message = "Card number must be 16 digits")
    @Pattern(
            regexp = "^\\d{16}$",
            message = "Card number must contain only digits"
    )
    private String cardNumber;

    @NotBlank(message = "Card type is required")
    private String cardType;

    @Min(value = 0, message = "Total limit cannot be negative")
    private int totalLimit;

    @Min(value = 0, message = "Amount used cannot be negative")
    private int amountUsed;

    @Min(value = 0, message = "Amount available cannot be negative")
    private int amountAvailable;
}
package com.micobank.account.constants;

public class AccountConstants {

    // Private constructor to prevent object creation
    private AccountConstants() {
    }

    // Account types
    public static final String SAVINGS = "Savings";
    public static final String CURRENT = "Current";

    // Default address
    public static final String ADDRESS = "Yashwin Hinjewadi, Phase 2, Pune- 411057";

    // Success status and messages
    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Account created successfully";

    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully";

    // Error status and message
    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 =
            "An error occurred. Please try again or contact dev team";
}

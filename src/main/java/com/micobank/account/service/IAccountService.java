package com.micobank.account.service;

import com.micobank.account.dto.CustomerDto;
import com.micobank.account.dto.AccountDto;

public interface IAccountService {

    void createAccount(CustomerDto customerDto);
    CustomerDto findAccountDetails(String mobileNumber);
    void updateAccount(AccountDto accountDto);
}

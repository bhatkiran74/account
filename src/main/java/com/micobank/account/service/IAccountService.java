package com.micobank.account.service;

import com.micobank.account.dto.CustomerDto;

public interface IAccountService {

    void createAccount(CustomerDto customerDto);
}

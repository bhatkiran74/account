package com.micobank.account.service.impl;

import com.micobank.account.repository.AccountRepository;
import com.micobank.account.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CusomerServiceImpl {



    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;



}

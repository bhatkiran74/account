package com.micobank.account.service.impl;

import com.micobank.account.constants.AccountConstants;
import com.micobank.account.dto.AccountDto;
import com.micobank.account.dto.CustomerDto;
import com.micobank.account.entity.Account;
import com.micobank.account.entity.Customer;
import com.micobank.account.exception.CustomerAlreadyExistException;
import com.micobank.account.exception.ResourceNotFoundException;
import com.micobank.account.mapper.AccountMapper;
import com.micobank.account.mapper.CustomerMapper;
import com.micobank.account.repository.AccountRepository;
import com.micobank.account.repository.CustomerRepository;
import com.micobank.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {


    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.toCustomer(customerDto, new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("Customer with mobile number " + customerDto.getMobileNumber() + " already exists.");
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");

        System.out.println("Creating account for customer: " + customer);
        Customer saveCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(saveCustomer));

    }

    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccountNumber = (long) (Math.random() * 10000000000L);
        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }

    @Override
    public CustomerDto findAccountDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException
                ("Customer", "MobileNumber", mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException
                ("Account", "CustomerId", customer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.toCustomerDto(customer, new CustomerDto());
        AccountDto accountDto = AccountMapper.mapToAccountDto(account, new AccountDto());

        customerDto.setAccountDto(accountDto);
        return customerDto;
    }
}

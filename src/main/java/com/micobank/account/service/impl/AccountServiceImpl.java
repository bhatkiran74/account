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

    // Inject repositories to perform database operations
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

        // Convert DTO object to Customer entity
        Customer customer = CustomerMapper.toCustomer(customerDto, new Customer());

        // Check whether customer already exists with the given mobile number
        Optional<Customer> optionalCustomer =
                customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistException(
                    "Customer with mobile number "
                            + customerDto.getMobileNumber()
                            + " already exists."
            );
        }

        System.out.println("Creating account for customer: " + customer);

        // Save customer details and create the corresponding account
        Customer saveCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(saveCustomer));
    }

    // Create a new account for the given customer
    private Account createNewAccount(Customer customer) {

        Account newAccount = new Account();

        // Set customer and account details
        newAccount.setCustomerId(customer.getCustomerId());

        long randomAccountNumber = (long) (Math.random() * 10000000000L);
        newAccount.setAccountNumber(randomAccountNumber);

        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);

        return newAccount;
    }

    @Override
    public CustomerDto findAccountDetails(String mobileNumber) {

        // Find customer using mobile number
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer",
                        "MobileNumber",
                        mobileNumber
                ));

        // Find account using customer ID
        Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Account",
                        "CustomerId",
                        customer.getCustomerId().toString()
                ));

        // Convert entities to DTOs
        CustomerDto customerDto =
                CustomerMapper.toCustomerDto(customer, new CustomerDto());

        AccountDto accountDto =
                AccountMapper.mapToAccountDto(account, new AccountDto());

        // Add account details to customer DTO
        customerDto.setAccountDto(accountDto);

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated = false;

        // Get account details from customer DTO
        AccountDto accountDto = customerDto.getAccountDto();

        if (accountDto != null) {

            // Find account using account number
            Account account = accountRepository.findById(accountDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Account",
                            "AccountNumber",
                            accountDto.getAccountNumber().toString()
                    ));

            // Update and save account details
            AccountMapper.mapToAccount(accountDto, account);
            account = accountRepository.save(account);

            // Get customer ID associated with the account
            Long customerId = account.getCustomerId();

            // Find and update customer details
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Customer",
                            "CustomerId",
                            customerId.toString()
                    ));

            CustomerMapper.toCustomer(customerDto, customer);
            customerRepository.save(customer);

            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        // Find customer using mobile number
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer",
                        "MobileNumber",
                        mobileNumber
                ));

        // Delete account first and then delete customer details
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }
}

package com.micobank.account.repository;

import com.micobank.account.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Find customer details using mobile number
    Optional<Customer> findByMobileNumber(String mobile);
}
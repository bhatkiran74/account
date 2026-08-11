package com.micobank.account.repository;

import com.micobank.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Find account details using customer ID
    Optional<Account> findByCustomerId(Long customerId);

    // Delete account using customer ID
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
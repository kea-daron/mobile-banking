package com.example.mobile_banking_api.repository;

import com.example.mobile_banking_api.domain.Account;
import com.example.mobile_banking_api.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findAllByCustomerPhoneNumber(String phoneNumber);

    boolean existsByAccountNumber(String accountNumber);
}

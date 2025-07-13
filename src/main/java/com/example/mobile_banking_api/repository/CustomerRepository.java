package com.example.mobile_banking_api.repository;

import com.example.mobile_banking_api.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // SELECT * FROM customers WHERE phone_number = ?
    Optional<Customer> findCustomerByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    String phoneNumber(String phoneNumber);
}

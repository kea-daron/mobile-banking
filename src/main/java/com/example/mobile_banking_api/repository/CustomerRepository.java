package com.example.mobile_banking_api.repository;

import com.example.mobile_banking_api.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}

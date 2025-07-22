package com.example.mobile_banking_api.repository;

import com.example.mobile_banking_api.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {

    Optional<AccountType> findByTypeName(String typeName);

    boolean existsByTypeName(String typeName);

}

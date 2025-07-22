package com.example.mobile_banking_api.repository;

import com.example.mobile_banking_api.domain.KYC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KYCRepository extends JpaRepository<KYC, Integer> {

    @Query("SELECT k FROM KYC k WHERE k.customer.phoneNumber = :phoneNumber AND k.isDeleted = false")
    Optional<KYC> findByCustomerPhoneNumber(String phoneNumber);

    boolean existsByNationalCardID(String nationalCardID);

}

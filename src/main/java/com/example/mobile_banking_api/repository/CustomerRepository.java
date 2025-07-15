package com.example.mobile_banking_api.repository;

import com.example.mobile_banking_api.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    //boolean existsByNationalCardId(String nationalCardId);

    List<Customer> findAllByIsDeletedFalse();

    // JPQL
    @Modifying
    @Query(value = """
       UPDATE Customer c SET c.isDeleted = TRUE
              WHERE c.phoneNumber = :phoneNumber
       """)
    void disableCustomerByPhoneNumber(String phoneNumber);

    @Query(value = """
        SELECT EXISTS (SELECT c.id
                FROM Customer c
                        WHERE c.phoneNumber = ?1)
        """)
    boolean isExistsByPhoneNumber(String phoneNumber);

    // Derived Query Method
    boolean existsByPhoneNumber(String phoneNumber);

    // SELECT * FROM customers WHERE phone_number = ?
    Optional<Customer> findCustomerByPhoneNumber(String phoneNumber);

    Optional<Customer> findByPhoneNumberAndIsDeletedFalse(String phoneNumber);

    boolean existsByEmail(String email);
}

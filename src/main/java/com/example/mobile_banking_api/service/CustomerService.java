package com.example.mobile_banking_api.service;

import com.example.mobile_banking_api.domain.KYC;
import com.example.mobile_banking_api.dto.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerService {


    // ------------------------ CUSTOMER SERVICE -------------------------------

    String generateAccountNumber();

    void disableCustomerByPhoneNumber(String phoneNumber);

    void disableByPhoneNumber(String phoneNumber);

    void deleteCustomerByPhoneNumber(String phoneNumber);

    CustomerResponse updateCustomerByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest);

    CustomerResponse findCustomerByPhoneNumber(String phoneNumber);

    List<CustomerResponse> findAll();

    CustomerResponse createNew(CreateCustomerRequest createCustomerRequest);


}

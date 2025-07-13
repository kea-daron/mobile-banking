package com.example.mobile_banking_api.service;

import com.example.mobile_banking_api.dto.*;

import java.util.List;

public interface CustomerService {


    // ------------------------ CUSTOMER SERVICE -------------------------------

    void deleteCustomerByPhoneNumber(String phoneNumber);

    CustomerRespose updateCustomerByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest);

    CustomerRespose findCustomerByPhoneNumber(String phoneNumber);

    List<CustomerRespose> findAll();

    CustomerRespose createNew(CreateCustomerRequest createCustomerRequest);


}

package com.example.mobile_banking_api.service;

import com.example.mobile_banking_api.domain.KYC;
import com.example.mobile_banking_api.dto.KYCResponse;

public interface KYCService {

    void verifyCustomerByPhoneNumber(String phoneNumber);

    KYCResponse getKYCByCustomerPhoneNumber(String phoneNumber);

}

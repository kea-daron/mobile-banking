package com.example.mobile_banking_api.service.impl;

import com.example.mobile_banking_api.domain.Account;
import com.example.mobile_banking_api.domain.Customer;
import com.example.mobile_banking_api.domain.KYC;
import com.example.mobile_banking_api.dto.KYCResponse;
import com.example.mobile_banking_api.repository.CustomerRepository;
import com.example.mobile_banking_api.repository.KYCRepository;
import com.example.mobile_banking_api.service.KYCService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class KYCServiceImpl implements KYCService {

    private final KYCRepository kycRepository;
    private final CustomerRepository customerRepository;

    @Override
    public KYCResponse getKYCByCustomerPhoneNumber(String phoneNumber) {

        KYC kyc = kycRepository.findByCustomerPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "KYC not found"));

        return new KYCResponse(
                kyc.getId(),
                kyc.getNationalCardID(),
                kyc.getIsVerified(),
                kyc.getIsDeleted(),
                kyc.getCustomer().getId()
        );


    }

    @Override
    public void verifyCustomerByPhoneNumber(String phoneNumber) {

        KYC kyc = kycRepository
                .findByCustomerPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "KYC not found"));
        kyc.setIsVerified(true);
        kycRepository.save(kyc);

    }

}

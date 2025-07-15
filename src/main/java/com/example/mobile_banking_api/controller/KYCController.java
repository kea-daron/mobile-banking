package com.example.mobile_banking_api.controller;

import com.example.mobile_banking_api.domain.KYC;
import com.example.mobile_banking_api.dto.KYCResponse;
import com.example.mobile_banking_api.repository.KYCRepository;
import com.example.mobile_banking_api.service.CustomerService;
import com.example.mobile_banking_api.service.KYCService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kycs")
public class KYCController {

    private final KYCService kycService;


    @PatchMapping("/{phoneNumber}/verify")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void verifyKYCByCustomerPhoneNumber(@PathVariable String phoneNumber) {
        kycService.verifyCustomerByPhoneNumber(phoneNumber);
    }


    @GetMapping("/{phoneNumber}")
    public KYCResponse getKycByPhoneNumber(@PathVariable String phoneNumber) {
        return kycService.getKYCByCustomerPhoneNumber(phoneNumber);
    }

}

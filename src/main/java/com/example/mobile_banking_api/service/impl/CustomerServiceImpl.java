package com.example.mobile_banking_api.service.impl;

import com.example.mobile_banking_api.domain.Customer;
import com.example.mobile_banking_api.domain.CustomerSegment;
import com.example.mobile_banking_api.domain.KYC;
import com.example.mobile_banking_api.dto.CreateCustomerRequest;
import com.example.mobile_banking_api.dto.CustomerResponse;
import com.example.mobile_banking_api.dto.UpdateCustomerRequest;
import com.example.mobile_banking_api.mapper.CustomerMapper;
import com.example.mobile_banking_api.repository.CustomerRepository;
import com.example.mobile_banking_api.repository.CustomerSegmentRepository;
import com.example.mobile_banking_api.repository.KYCRepository;
import com.example.mobile_banking_api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final KYCRepository kycRepository;
    private final CustomerSegmentRepository customerSegmentRepository;


    @Override
    public String generateAccountNumber() {
        long timestamp = System.currentTimeMillis();
        int random = (int) (Math.random() * 1000);
        return String.format("%09d%03d", timestamp % 1_000_000_000, random);
    }

    @Transactional
    @Override
    public void disableCustomerByPhoneNumber(String phoneNumber) {
        if (!customerRepository.isExistsByPhoneNumber(phoneNumber)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer phone number not found");
        }
        customerRepository.disableCustomerByPhoneNumber(phoneNumber);
    }

    @Transactional
    @Override
    public void disableByPhoneNumber(String phoneNumber) {
        if (!customerRepository.isExistsByPhoneNumber(phoneNumber)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer phone number not found");
        }
        customerRepository.disableCustomerByPhoneNumber(phoneNumber);
    }

    @Override
    public void deleteCustomerByPhoneNumber(String phoneNumber) {

        Customer customer = customerRepository
                .findCustomerByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Customer phone number not found"));

        customerRepository.delete(customer);
    }

    @Override
    public CustomerResponse updateCustomerByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest) {

        Customer customer = customerRepository
                .findCustomerByPhoneNumber(phoneNumber)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Customer phone number not found"));
        customerMapper.toCustomerPartially(updateCustomerRequest, customer);

        customer = customerRepository.save(customer);

        return customerMapper.fromCustomer(customer);
    }

    @Override
    public CustomerResponse findCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository
                .findByPhoneNumberAndIsDeletedFalse(phoneNumber)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer phone number not found"));
    }

    @Override
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAllByIsDeletedFalse();
        return customers
                .stream()
                .map(customerMapper::fromCustomer)
                .toList();
    }

    @Override
    public CustomerResponse createNew(CreateCustomerRequest createCustomerRequest) {
        //validat email
        if(customerRepository.existsByEmail(createCustomerRequest.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        //validate phone number
        if(customerRepository.isExistsByPhoneNumber(createCustomerRequest.phoneNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        }

        // validation national card id
        if (kycRepository.existsByNationalCardID(createCustomerRequest.nationalCardId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "National Card ID already exists");
        }

        // validation customer segment
        CustomerSegment customerSegment = customerSegmentRepository
                .findBySegment(createCustomerRequest.customerSegment())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT,
                        "Customer segment not found!"));

        //Map data from DTO
        Customer customer = customerMapper.toCustomer(createCustomerRequest);

        //Prepare KYC of customer
        KYC kyc = new KYC();
        kyc.setNationalCardID(createCustomerRequest.nationalCardId());
        kyc.setIsVerified(false);
        kyc.setIsDeleted(false);
        kyc.setCustomer(customer);

        customer.setIsDeleted(false);
        customer.setAccounts(new ArrayList<>());
        customer.setCustomerSegment(customerSegment);
        customer.setKyc(kyc);


        kyc.setNationalCardID(createCustomerRequest.nationalCardId());
        kyc.setIsVerified(false);
        kyc.setIsDeleted(false);
        kyc.setCustomer(customer);
        customer.setKyc(kyc);

        log.info("Customer before save: {}", customer.getId());
        customer = customerRepository.save(customer);

        log.info("Customer after save: {}", customer.getId());
        customer = customerRepository.save(customer);

        return customerMapper.fromCustomer(customer);
    }


}

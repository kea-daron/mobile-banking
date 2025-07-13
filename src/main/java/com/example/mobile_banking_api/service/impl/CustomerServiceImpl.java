package com.example.mobile_banking_api.service.impl;

import com.example.mobile_banking_api.domain.Customer;
import com.example.mobile_banking_api.dto.CreateCustomerRequest;
import com.example.mobile_banking_api.dto.CustomerRespose;
import com.example.mobile_banking_api.dto.UpdateCustomerRequest;
import com.example.mobile_banking_api.mapper.CustomerMapper;
import com.example.mobile_banking_api.repository.CustomerRepository;
import com.example.mobile_banking_api.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerRespose createNew(CreateCustomerRequest createCustomerRequest) {

        // validate email
        if (customerRepository.existsByEmail(createCustomerRequest.email())){
            throw  new ResponseStatusException(HttpStatus.CONFLICT,
                    "Email already exists");
        }

        // validate phone number
        if (customerRepository.existsByPhoneNumber(createCustomerRequest.phoneNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Phone number already exists");
        }

        Customer customer = customerMapper.toCustomer(createCustomerRequest);
        customer.setIsDeleted(false);
        customer.setAccounts(new ArrayList<>());

        log.info("Customer before save: {}", customer.getId());
        customer = customerRepository.save(customer);
        log.info("Customer after save: {}", customer.getId());

        return customerMapper.fromCustomer(customer);
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
    public CustomerRespose updateCustomerByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest) {

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
    public CustomerRespose findCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository
                .findCustomerByPhoneNumber(phoneNumber)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer phone number not found"));
    }

    @Override
    public List<CustomerRespose> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers
                .stream()
                .map(customerMapper::fromCustomer)
                .toList();
    }

}

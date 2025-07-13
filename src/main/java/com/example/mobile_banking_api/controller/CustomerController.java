package com.example.mobile_banking_api.controller;

import com.example.mobile_banking_api.dto.CreateCustomerRequest;
import com.example.mobile_banking_api.dto.CustomerRespose;
import com.example.mobile_banking_api.dto.UpdateCustomerRequest;
import com.example.mobile_banking_api.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{phoneNumber}")
    public  void deleteCustomerByPhoneNumber(@PathVariable String phoneNumber) {
        customerService.deleteCustomerByPhoneNumber(phoneNumber);
    }

    @PatchMapping("/{phoneNumber}")
    public CustomerRespose updateCustomerByPhoneNumber(@PathVariable String phoneNumber, @RequestBody UpdateCustomerRequest updateCustomerRequest) {
        return customerService.updateCustomerByPhoneNumber(phoneNumber, updateCustomerRequest);
    }

    @GetMapping("/{phoneNumber}")
    public CustomerRespose findCustomerByPhoneNumber(@PathVariable String phoneNumber) {
        return customerService.findCustomerByPhoneNumber(phoneNumber);
    }

    @GetMapping
    public List<CustomerRespose> findAll(){
        return customerService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CustomerRespose createNew(@Valid @RequestBody CreateCustomerRequest createCustomerRequest){
        return customerService.createNew(createCustomerRequest);
    }

}

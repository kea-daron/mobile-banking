package com.example.mobile_banking_api.controller;


import com.example.mobile_banking_api.dto.AccountResponse;
import com.example.mobile_banking_api.dto.CreateAccountRequest;
import com.example.mobile_banking_api.dto.CustomerRespose;
import com.example.mobile_banking_api.dto.UpdateAccountRequest;
import com.example.mobile_banking_api.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AccountResponse createNewAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createNewAccount(createAccountRequest);
    }

    @GetMapping
    public List<AccountResponse> findAll(){
        return accountService.findAll();
    }

    @GetMapping("/{accountNumber}")
    public AccountResponse findAccountByNumber(@PathVariable String accountNumber) {
        return accountService.findAccountByAccountNumber(accountNumber);
    }

    @GetMapping("/customer")
    public List<AccountResponse> findAccountsByCustomerPhoneNumber(@RequestParam("phone") String phoneNumber) {
        return accountService.findAccountsByCustomerPhoneNumber(phoneNumber);
    }

    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountByAccountNumber(@PathVariable String accountNumber) {
        accountService.deleteAccountByAccountNumber(accountNumber);
    }

    @PutMapping("/{accountNumber}")
    public AccountResponse updateAccountByAccountNumber(
            @PathVariable String accountNumber,
            @RequestBody UpdateAccountRequest updateAccountRequest
    ) {
        return accountService.updateAccountByAccountNumber(accountNumber, updateAccountRequest);
    }
}

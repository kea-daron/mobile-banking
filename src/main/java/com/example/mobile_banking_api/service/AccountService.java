package com.example.mobile_banking_api.service;

import com.example.mobile_banking_api.dto.AccountResponse;
import com.example.mobile_banking_api.dto.CreateAccountRequest;
import com.example.mobile_banking_api.dto.UpdateAccountRequest;

import java.util.List;

public interface AccountService {

    /**
     * author : KEA DARON FULL STACK MORNING
     *
     */
    AccountResponse createNewAccount(CreateAccountRequest createAccountRequest);

    List<AccountResponse> findAll();

    AccountResponse findAccountByAccountNumber(String accountNumber);

    List<AccountResponse> findAccountsByCustomerPhoneNumber(String phoneNumber);

    void deleteAccountByAccountNumber(String accountNumber);

    AccountResponse updateAccountByAccountNumber(String accountNumber, UpdateAccountRequest request);

}

package com.example.mobile_banking_api.service;

import com.example.mobile_banking_api.dto.AccountTypeResponse;
import com.example.mobile_banking_api.dto.CreateAccountTypeRequest;

import java.util.List;

public interface AccountTypeService {

    AccountTypeResponse createNewAccountType(CreateAccountTypeRequest request);

    List<AccountTypeResponse> getAllAccountTypes();

}

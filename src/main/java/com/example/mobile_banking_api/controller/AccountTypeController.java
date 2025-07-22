package com.example.mobile_banking_api.controller;


import com.example.mobile_banking_api.dto.AccountTypeResponse;
import com.example.mobile_banking_api.dto.CreateAccountTypeRequest;
import com.example.mobile_banking_api.service.AccountService;
import com.example.mobile_banking_api.service.AccountTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accountTypes")
@RequiredArgsConstructor
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    @GetMapping
    public List<AccountTypeResponse> getAll() {
        return accountTypeService.getAllAccountTypes();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AccountTypeResponse createNewAccountType(@Valid @RequestBody CreateAccountTypeRequest createAccountTypeRequest) {

        return accountTypeService.createNewAccountType(createAccountTypeRequest);

    }


}

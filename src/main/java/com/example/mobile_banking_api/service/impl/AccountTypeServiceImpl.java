package com.example.mobile_banking_api.service.impl;

import com.example.mobile_banking_api.domain.AccountType;
import com.example.mobile_banking_api.dto.AccountTypeResponse;
import com.example.mobile_banking_api.dto.CreateAccountTypeRequest;
import com.example.mobile_banking_api.repository.AccountTypeRepository;
import com.example.mobile_banking_api.service.AccountTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;

    @Override
    public AccountTypeResponse createNewAccountType(CreateAccountTypeRequest createAccountTypeRequest) {

        if (createAccountTypeRequest.typeName() == null || createAccountTypeRequest.typeName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "typeName is required");
        }

        if (accountTypeRepository.existsByTypeName(createAccountTypeRequest.typeName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account type already exists");
        }

        AccountType accountType = new AccountType();
        accountType.setTypeName(createAccountTypeRequest.typeName());
        AccountType saved = accountTypeRepository.save(accountType);

        return new AccountTypeResponse(saved.getId(), saved.getTypeName());
    }

    @Override
    public List<AccountTypeResponse> getAllAccountTypes() {
        return accountTypeRepository.findAll().stream()
                .map(accountType -> new AccountTypeResponse(
                        accountType.getId(),
                        accountType.getTypeName()
                ))
                .toList();
    }
}

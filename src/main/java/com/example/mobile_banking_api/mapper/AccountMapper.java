package com.example.mobile_banking_api.mapper;

import com.example.mobile_banking_api.domain.Account;
import com.example.mobile_banking_api.dto.AccountResponse;

import com.example.mobile_banking_api.dto.CreateAccountRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toAccount(CreateAccountRequest createAccountRequest);

    @Mapping(source = "customer.fullName", target = "customerName")
    @Mapping(source = "accountType.typeName", target = "accountTypeName")

    AccountResponse fromAccount(Account account);


}


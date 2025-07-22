package com.example.mobile_banking_api.dto;

import com.example.mobile_banking_api.util.CurrencyUtil;

import java.math.BigDecimal;


public record CreateAccountRequest(

        String accountNumber,
        String accountName,
        CurrencyUtil accountCurrency,
        BigDecimal balance,
        String customerPhoneNumber,
        String accountTypeName

) {
}

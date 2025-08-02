package com.example.mobile_banking_api.dto;

import com.example.mobile_banking_api.util.CurrencyUtil;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;


public record CreateAccountRequest(

        @NotBlank(message = "Account number is required")
        String accountNumber,
        String accountName,
        CurrencyUtil accountCurrency,
        BigDecimal balance,
        String customerPhoneNumber,
        String accountTypeName

) {
}

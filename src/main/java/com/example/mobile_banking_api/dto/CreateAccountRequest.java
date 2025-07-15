package com.example.mobile_banking_api.dto;

import java.math.BigDecimal;


public record CreateAccountRequest(

        String accountCurrency,
        BigDecimal balance,
        String customerPhoneNumber,
        String accountTypeName

) {
}

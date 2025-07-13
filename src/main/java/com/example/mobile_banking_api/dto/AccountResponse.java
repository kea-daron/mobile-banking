package com.example.mobile_banking_api.dto;

import java.math.BigDecimal;

public record AccountResponse(

        String accountNumber,
        String accountCurrency,
        BigDecimal balance,
        Boolean isDeleted,
        String customerName,
        String accountTypeName

) {
}

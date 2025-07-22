package com.example.mobile_banking_api.dto;

import java.math.BigDecimal;

public record AccountResponse(

        String accountNumber,
        String accountName,
        String accountCurrency,
        BigDecimal balance,
        BigDecimal overLimit,
        Boolean isDeleted,
        String customerName,
        String accountTypeName,
        Boolean isHide

) {
}

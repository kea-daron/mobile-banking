package com.example.mobile_banking_api.dto;
import java.math.BigDecimal;

public record UpdateAccountRequest(

        String accountCurrency,
        BigDecimal balance,
        String accountTypeName

) {
}

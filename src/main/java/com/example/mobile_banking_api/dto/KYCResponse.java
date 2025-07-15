package com.example.mobile_banking_api.dto;

public record KYCResponse(

        Integer id,
        String nationalCardID,
        Boolean isVerified,
        Boolean isDeleted,
        Integer customerId

) {
}

package com.example.mobile_banking_api.dto;

import lombok.Builder;

@Builder
public record CustomerResponse(

        String fullName,
        String gender,
        String email

) {
}

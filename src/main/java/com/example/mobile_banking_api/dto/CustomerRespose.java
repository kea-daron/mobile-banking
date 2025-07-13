package com.example.mobile_banking_api.dto;

import lombok.Builder;

@Builder
public record CustomerRespose(

        String fullName,
        String gender,
        String email

) {
}

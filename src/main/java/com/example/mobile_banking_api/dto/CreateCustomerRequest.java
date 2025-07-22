package com.example.mobile_banking_api.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateCustomerRequest(

        @NotBlank (message = "Full name is required")
        String fullName,

        @NotBlank (message = "Gender is request")
        String gender,

        LocalDate dob,

        String email,
        String phoneNumber,
        String remark,

        String nationalCardId,
        String customerSegment

) {
}

package com.example.mobile_banking_api.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest(

        @NotBlank (message = "Full name is required")
        String fullName,

        @NotBlank (message = "Gender is request")
        String gender,

        String email,
        String phoneNumber,
        String remark,

        @NotBlank(message = "National Card ID is required")
        String nationalCardId,

        @NotBlank(message = "Segment is required")
        String segment

) {
}

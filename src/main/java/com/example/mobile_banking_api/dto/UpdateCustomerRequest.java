package com.example.mobile_banking_api.dto;


public record UpdateCustomerRequest(

        String fullName,
        String gender,
        String remark,
        String email,
        String phoneNumber,
        String nationalCardID,
        String segment

) {
}

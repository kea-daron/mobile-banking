package com.example.mobile_banking_api.dto;

import lombok.Builder;

import java.nio.file.FileStore;

@Builder
public record MediaResponse(

        String name,
        String mimeTypeFile,
        String uri,
        Long size

) {
}

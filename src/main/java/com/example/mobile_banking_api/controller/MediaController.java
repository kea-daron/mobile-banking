package com.example.mobile_banking_api.controller;

import com.example.mobile_banking_api.dto.MediaResponse;
import com.example.mobile_banking_api.service.MediaService;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping
    public MediaResponse upload(@RequestPart MultipartFile file) {
        return mediaService.upload(file);
    }

    @PostMapping("/multiple")
    public List<MediaResponse> uploadMultiple(@RequestPart MultipartFile[] files) {
        return mediaService.uploadMultiple(files);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadByName(@PathVariable String filename) {
        Resource resource = mediaService.downloadByName(filename);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

    @DeleteMapping("/{filename}")
    public ResponseEntity<String> deleteByName(@PathVariable String filename) {
        boolean deleted = mediaService.deleteByName(filename);
        if (deleted) {
            return ResponseEntity.ok("File deleted successfully: " + filename);
        } else {
            return ResponseEntity.badRequest().body("Failed to delete file: " + filename);
        }
    }

}

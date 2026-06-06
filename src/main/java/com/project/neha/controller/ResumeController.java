package com.project.neha.controller;

import com.project.neha.service.ResumeUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeUploadService resumeUploadService;

    @PostMapping(
            value = "/upload-resume",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String uploadResume(
            @RequestParam("file") MultipartFile file,
            Authentication authentication
    ) throws Exception {

        String email = authentication.getName();

        return resumeUploadService.uploadResume(file, email);
    }
}

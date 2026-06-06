package com.project.neha.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.project.neha.entity.User;
import com.project.neha.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResumeUploadService {

    private final Cloudinary cloudinary;
    private final UserRepository userRepository;

    public String uploadResume(MultipartFile file, String email) throws Exception {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        if (!file.getContentType().equals("application/pdf")) {
            throw new RuntimeException("Only PDF files are allowed");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "placementhub/resumes",
                        "resource_type", "raw"
                )
        );

        String resumeUrl = uploadResult.get("secure_url").toString();

        user.setResumeUrl(resumeUrl);
        userRepository.save(user);

        return resumeUrl;
    }
}

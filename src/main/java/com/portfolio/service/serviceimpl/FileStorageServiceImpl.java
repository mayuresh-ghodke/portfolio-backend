package com.portfolio.service.serviceimpl;

import com.portfolio.service.FileStorageService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${file.upload.dir}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
            System.out.println("UPLOAD FOLDER CREATED AT: " + directory.getAbsolutePath());
        } else {
            System.out.println("UPLOAD FOLDER ALREADY EXISTS: " + directory.getAbsolutePath());
        }
    }

    public String saveFile(MultipartFile file) {
        try {
            // 1. Unique file name
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            // 2. Correct path
            Path path = Paths.get(uploadDir, fileName);

            // 3. Save file
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // 4. Return static file URL
            return "/uploads/" + fileName;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not save file", e);
        }
    }
}


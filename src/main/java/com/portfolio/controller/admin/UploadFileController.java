package com.portfolio.controller.admin;

import com.portfolio.model.UploadFile;
import com.portfolio.service.UploadFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin/files")
public class UploadFileController {

    @Autowired
    private UploadFileService uploadFileService;

    // ------------------ SAVE FILE ------------------
    @PostMapping("/save")
    public ResponseEntity<UploadFile> saveFile(
            @RequestParam("description") String description,
            @RequestParam("fileType") String fileType,
            @RequestParam("hidden") boolean hidden,
            @RequestParam("uploadedFile") MultipartFile uploadedFile)
    {
        UploadFile file = new UploadFile();
        file.setDescription(description);
        file.setFileType(fileType);
        file.setHidden(hidden);

        UploadFile saved = uploadFileService.saveUploadFile(file, uploadedFile);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ------------------ GET BY ID ------------------
    @GetMapping("/get/{id}")
    public ResponseEntity<UploadFile> getFileById(@PathVariable("id") int id) {
        UploadFile file = uploadFileService.getFileById(id);
        return ResponseEntity.ok(file);
    }

    // ------------------ GET BY TYPE ------------------
    @GetMapping("/get/type/{type}")
    public ResponseEntity<List<UploadFile>> getByType(@PathVariable("type") String type) {
        List<UploadFile> fileList = uploadFileService.getAllUploadFilesByType(type);
        return ResponseEntity.ok(fileList);
    }

    // ------------------ DELETE ------------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteFile(@PathVariable("id") int id) {
        boolean deleted = uploadFileService.deleteUploadFile(id);

        return deleted
                ? ResponseEntity.ok(true)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}

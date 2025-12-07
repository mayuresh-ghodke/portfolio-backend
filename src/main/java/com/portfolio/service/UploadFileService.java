package com.portfolio.service;

import com.portfolio.model.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadFileService {

    UploadFile saveUploadFile(UploadFile uploadFile, MultipartFile multipartFile);
    boolean deleteUploadFile(int fileId);
    List<UploadFile> getAllUploadFilesByType(String fileType);
    UploadFile getFileById(int fileId);

}

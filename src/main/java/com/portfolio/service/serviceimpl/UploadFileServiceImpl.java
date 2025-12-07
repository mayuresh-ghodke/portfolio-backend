package com.portfolio.service.serviceimpl;

import com.portfolio.model.UploadFile;
import com.portfolio.repository.UploadFileRepository;
import com.portfolio.service.FileStorageService;
import com.portfolio.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private UploadFileRepository uploadFileRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public UploadFile saveUploadFile(UploadFile uploadedFile,
                                     MultipartFile multipartFile) {
        String savedFileName = fileStorageService.saveFile(multipartFile);
        uploadedFile.setFileName(savedFileName);
        return uploadFileRepository.save(uploadedFile);
    }

    @Override
    public boolean deleteUploadFile(int fileId) {
        getFileById(fileId);
        uploadFileRepository.deleteById(fileId);
        return true;
    }

    @Override
    public List<UploadFile> getAllUploadFilesByType(String fileType) {
        return uploadFileRepository.findAll()
                .stream()
                .filter(file->file.getFileType().equalsIgnoreCase(fileType))
                .toList();
    }

    @Override
    public UploadFile getFileById(int fileId) {
        UploadFile uploadFile = uploadFileRepository.findById(fileId).get();
        if(uploadFile == null){
            throw new RuntimeException("File does not exist.");
        }
        return uploadFile;
    }
}

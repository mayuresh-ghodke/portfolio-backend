package com.portfolio.service.serviceimpl;

import com.portfolio.model.AboutInfo;
import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.repository.AboutInfoRepository;
import com.portfolio.service.AboutInfoService;
import com.portfolio.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Base64;
import java.util.List;

@Service
public class AboutInfoServiceImpl implements AboutInfoService {

    @Autowired
    private AboutInfoRepository aboutInfoRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public AboutInfo saveOrUpdateWithFile(String title, String bio, String info, MultipartFile imgFile) {

        AboutInfo about = new AboutInfo();
        about.setAboutTitle(title);
        about.setAboutInfo(info);
        about.setAboutBio(bio);

        // If image uploaded
        if (imgFile != null && !imgFile.isEmpty()) {

            try {
                String fileName = fileStorageService.saveFile(imgFile);

                // Store file name in DB
                about.setImgUrl(fileName);

            } catch (Exception e) {
                throw new RuntimeException("Failed to upload image: " + e.getMessage());
            }
        }

        return aboutInfoRepository.save(about);
    }

    @Override
    public AboutInfo save(AboutInfo aboutInfo) {
        return aboutInfoRepository.save(aboutInfo);
    }



    @Override
    public AboutInfo getAboutInfo() {
        List<AboutInfo> list = aboutInfoRepository.findAll();
        if(list.isEmpty()){
            throw new ResourceNotFoundException("About information is not present.");
        }
        return list.stream().findFirst().get();
    }

    @Override
    public AboutInfo updateAboutInfo(AboutInfo aboutInfo) {
        getAboutInfo();
        return aboutInfoRepository.save(aboutInfo);
    }

    @Override
    public boolean deleteAboutInfo() {
        AboutInfo aboutInfo = getAboutInfo();
        aboutInfoRepository.delete(aboutInfo);
        return true;
    }
}

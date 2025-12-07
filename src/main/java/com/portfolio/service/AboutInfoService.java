package com.portfolio.service;

import com.portfolio.model.AboutInfo;
import org.springframework.web.multipart.MultipartFile;

public interface AboutInfoService {

    AboutInfo saveOrUpdateWithFile(String aboutTitle, String bio, String aboutInfoText, MultipartFile imgFile);
    AboutInfo save(AboutInfo aboutInfo);
    AboutInfo getAboutInfo();
    AboutInfo updateAboutInfo(AboutInfo aboutInfo);
    boolean deleteAboutInfo();
}

package com.portfolio.controller.admin;

import com.portfolio.model.AboutInfo;
import com.portfolio.service.AboutInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin/about")
public class AboutController {

    @Autowired
    private AboutInfoService aboutInfoService;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AboutInfo> saveAboutInfo(
            @RequestParam("aboutTitle") String aboutTitle,
            @RequestParam("aboutBio") String aboutBio,
            @RequestParam("aboutInfo") String aboutInfoText,
            @RequestParam(value = "imgFile", required = false) MultipartFile imgFile
    ) {
        AboutInfo saved = aboutInfoService.saveOrUpdateWithFile(aboutTitle, aboutBio, aboutInfoText, imgFile);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @GetMapping("/get")
    public ResponseEntity<AboutInfo> getAboutInfo(){
        AboutInfo aboutInfo = aboutInfoService.getAboutInfo();
        return new ResponseEntity<>(aboutInfo, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteAboutInfo(){
        boolean result = aboutInfoService.deleteAboutInfo();
        if(result)
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

}

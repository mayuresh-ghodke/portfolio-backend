package com.portfolio.controller;

import com.portfolio.dto.OwnerProfileDto;
import com.portfolio.dto.ProfileDto;
import com.portfolio.dto.SkillDto;
import com.portfolio.model.*;
import com.portfolio.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class PublicController {

    private final ContactService contactService;
    private final ProjectService projectService;
    private final SkillService skillService;
    private final AboutInfoService aboutInfoService;
    private final EducationService educationService;
    private final ExperienceService experienceService;
    private final ProfileService profileService;
    private final UploadFileService uploadFileService;

    // Contact POST
    @PostMapping("/contact/save")
    public ResponseEntity<Contact> saveContactMessage(@RequestBody Contact contactMessage){
        Contact contact = contactService.saveContactMessage(contactMessage);
        return ResponseEntity.ok(contact);
    }

    // About GET
    @GetMapping("/about")
    public ResponseEntity<AboutInfo> getAboutInfo(){
        return ResponseEntity.ok(aboutInfoService.getAboutInfo());
    }

    // Education GET
    @GetMapping("/education")
    public ResponseEntity<List<Education>> getAllEducation(){
        return ResponseEntity.ok(educationService.getAllEducation());
    }

    // Projects GET
    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects(){
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    // Skills GET
    @GetMapping("/skills")
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> list = skillService.getAllSkills();
        list.stream().forEach(System.out::println);
        
        return ResponseEntity.ok(list);
    }

    // GET ALL
    @GetMapping("/experiences")
    public ResponseEntity<List<Experience>> getAllExperiences() {
        List<Experience> experiences = experienceService.getAllExperience();
        return ResponseEntity.ok(experiences);
    }

    @GetMapping("/profile")
    public ResponseEntity<OwnerProfileDto> getMyProfile() {
        OwnerProfileDto ownerProfileDto = profileService.getOwnerProfile();
        if (ownerProfileDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(ownerProfileDto);
    }

    @GetMapping("/files/get/by-type/{type}")
    public ResponseEntity<List<UploadFile>> getFilesByType(@PathVariable("type") String type){
        List<UploadFile> fileList = uploadFileService.getAllUploadFilesByType(type);
        if(fileList.isEmpty()){
            return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(fileList, HttpStatus.OK);
        }
    }

}

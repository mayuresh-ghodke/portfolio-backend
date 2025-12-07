package com.portfolio.controller.admin;

import com.portfolio.model.Education;
import com.portfolio.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin/education")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @PostMapping("/save")
    public ResponseEntity<Education> saveEducation(@RequestBody Education education){
        Education savedEducation = educationService.saveEducation(education);
        return new ResponseEntity<>(savedEducation, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Education> getEducationById(@PathVariable("id") int eduId){
        Education education = educationService.getEducationById(eduId);
        if(education != null){
            return new ResponseEntity<>(education, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Education>> getAllEducation(){
        List<Education> list = educationService.getAllEducation();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Education> updateEducation(@PathVariable("id") int eduId,
                                                     @RequestBody Education education){
        Education updated = educationService.updateEducation(eduId, education);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteEducation(@PathVariable("id") int eduId){
        boolean result = educationService.deleteEducation(eduId);
        if(result)
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}

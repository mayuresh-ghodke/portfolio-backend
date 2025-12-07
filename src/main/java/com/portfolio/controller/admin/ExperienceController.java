package com.portfolio.controller.admin;

import com.portfolio.model.Experience;
import com.portfolio.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/experience")
@CrossOrigin(origins = "http://localhost:3000")   // optional, if frontend calls from React
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    // CREATE
    @PostMapping("/save")
    public ResponseEntity<Experience> addExperience(@RequestBody Experience experience) {
        Experience saved = experienceService.saveExperience(experience);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // GET ALL
    @GetMapping("/get/all")
    public ResponseEntity<List<Experience>> getAllExperiences() {
        List<Experience> experiences = experienceService.getAllExperience();
        return ResponseEntity.ok(experiences);
    }

    // GET BY ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Experience> getExperience(@PathVariable int id) {
        Experience experience = experienceService.getExperienceById(id);
        return ResponseEntity.ok(experience);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<Experience> updateExperience(
            @PathVariable int id,
            @RequestBody Experience experience) {

        Experience updated = experienceService.updateExperience(id, experience);
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExperience(@PathVariable int id) {
        experienceService.deleteExperience(id);
        return ResponseEntity.ok("Experience deleted successfully with ID: " + id);
    }
}


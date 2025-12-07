package com.portfolio.controller.admin;

import com.portfolio.dto.ProfileDto;
import com.portfolio.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/admin/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // READ (Current logged-in user)
    @GetMapping("/get")
    public ResponseEntity<ProfileDto> getMyProfile() {
        ProfileDto profileDto = profileService.getMyProfile();
        if (profileDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(profileDto);
    }

    // UPDATE (Current logged-in user)
    @PutMapping("/update")
    public ResponseEntity<ProfileDto> updateMyProfile(@RequestBody ProfileDto profileDto) {
        ProfileDto updatedProfile = profileService.updateProfile(profileDto);
        if (updatedProfile == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedProfile);
    }

    // DELETE (Current logged-in user)
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMyProfile() {
        boolean deleted = profileService.deleteMyProfile();
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
}

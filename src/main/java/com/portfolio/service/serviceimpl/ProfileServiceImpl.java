package com.portfolio.service.serviceimpl;

import com.portfolio.dto.OwnerProfileDto;
import com.portfolio.dto.ProfileDto;
import com.portfolio.exception.ProfileNotFoundException;
import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.mapper.Mapper;
import com.portfolio.model.Profile;
import com.portfolio.repository.ProfileRepository;
import com.portfolio.service.EmailService;
import com.portfolio.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService{

    private final ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public ProfileDto saveProfile(ProfileDto profileDto) {
        Profile profile = Mapper.mapProfileDtoToProfile(profileDto);
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        profile.setEmail(profileDto.getEmail());
        Profile savedProfile = profileRepository.save(profile);

        if(savedProfile != null) {
            emailService.sendRegistrationEmail(
                    profile.getEmail(),
                    profile.getFirstName()
            );
        }
        return Mapper.mapProfileToProfileDto(savedProfile);
    }

    @Override
    public ProfileDto getMyProfile() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if ("anonymousUser".equals(email)) {
            throw new ProfileNotFoundException("You must be logged in to view your profile.");
        }

        Profile profile = profileRepository.findByEmail(email)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found for email: " + email));

        System.out.println("Get my profile, Profile password: " + profile.getPassword());
        return Mapper.mapProfileToProfileDto(profile);
    }

    @Override
    public ProfileDto updateProfile(ProfileDto dto) {

        // Get logged-in username (email)
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        // Fetch profile using logged-in user's email  (THIS IS CORRECT)
        Profile profile = profileRepository.findByEmail(username).get();

        // Fetch existing profile using userId (THIS IS ALSO CORRECT)
        Profile existing = profileRepository.findByUserId(profile.getUserId());
        if (existing == null) return null;

        // Update all fields
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setFathersName(dto.getFathersName());
        existing.setDob(dto.getDob());
        existing.setMobile(dto.getMobile());
        existing.setEmail(dto.getEmail());

        System.out.println("Updation DTO email: " + dto.getEmail());
        System.out.println("Updation DTO password: " + dto.getPassword());

        System.out.println("To update existing email: "+ existing.getEmail());
        System.out.println("To update existing password: "+ existing.getPassword());

        existing.setCity(dto.getCity());
        existing.setAddress(dto.getAddress());

        existing.setLinkedInUrl(dto.getLinkedInUrl());
        existing.setGithubUrl(dto.getGithubUrl());
        existing.setInstaUrl(dto.getInstaUrl());
        existing.setFbUrl(dto.getFbUrl());

        // Save updates (WILL UPDATE, NOT INSERT)
        Profile updated = profileRepository.save(existing);
        System.out.println("After saving updated (Profile email): " + updated.getEmail());
        System.out.println("After saving updated (Profile password): " + updated.getPassword());

        return Mapper.mapProfileToProfileDto(updated);
    }



    @Override
    public boolean deleteMyProfile() {
        List<Profile> list = profileRepository.findAll();
        if(list.isEmpty()){
            return false;
        }
        Profile profile = list.stream().findFirst().get();
        profileRepository.deleteById(profile.getUserId());
        return true;
    }

    @Override
    public OwnerProfileDto getOwnerProfile() {
        List<Profile> list = profileRepository.findAll();
        if(list.isEmpty()){
            return null;
        }
        Profile profile = list.stream().findFirst().get();
        return Mapper.mapProfileToOwnerProfileDto(profile);
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        Profile profile = profileRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("Email not existing to update the password."));
        profile.setPassword(passwordEncoder.encode(newPassword));
        profileRepository.save(profile);
        System.out.println("Profile password: " + profile.getPassword());
        return true;
    }
}

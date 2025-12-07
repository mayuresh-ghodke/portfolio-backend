package com.portfolio.mapper;

import com.portfolio.dto.OwnerProfileDto;
import com.portfolio.dto.ProfileDto;
import com.portfolio.dto.SkillDto;
import com.portfolio.model.Profile;
import com.portfolio.model.Skill;

public class Mapper {

    // Entity -> DTO
    public static ProfileDto mapProfileToProfileDto(Profile profile) {
        if (profile == null) {
            return null;
        }

        return ProfileDto.builder()
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .fathersName(profile.getFathersName())
                .dob(profile.getDob())
                .city(profile.getCity())
                .address(profile.getAddress())
                .mobile(profile.getMobile())
                .email(profile.getEmail())
                .password(profile.getPassword())
                .linkedInUrl(profile.getLinkedInUrl())
                .fbUrl(profile.getFbUrl())
                .githubUrl(profile.getGithubUrl())
                .instaUrl(profile.getInstaUrl())
                .build();
    }

    // DTO -> Entity
    public static Profile mapProfileDtoToProfile(ProfileDto dto) {
        if (dto == null) {
            return null;
        }

        return Profile.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .fathersName(dto.getFathersName())
                .dob(dto.getDob())
                .city(dto.getCity())
                .address(dto.getAddress())
                .mobile(dto.getMobile())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .linkedInUrl(dto.getLinkedInUrl())
                .fbUrl(dto.getFbUrl())
                .githubUrl(dto.getGithubUrl())
                .instaUrl(dto.getInstaUrl())
                .build();
    }

    // Skill and SkillDto

    // SkillDto -> Skill
//    public static Skill skillDtoToSkill(SkillDto skillDto){
//        if(skillDto == null){
//            return null;
//        }
//        return Skill.builder()
//                .skillId(skillDto.getSkillId())
//                .iconName(skillDto.getIconName())
//                .skillName(skillDto.getSkillName())
//                .category(skillDto.getCategory())
//                .build();
//    }

    // Skill -> SkillDto
//    public static SkillDto skillToSkillDto(Skill skill){
//        if(skill == null){
//            return null;
//        }
//        return SkillDto.builder()
//                .skillId(skill.getSkillId())
//                .skillName(skill.getSkillName())
//                .iconName(skill.getIconName())
//                .category(skill.getCategory())
//                .build();
//    }

    // Profile -> OwnerProfileDto
    public static OwnerProfileDto mapProfileToOwnerProfileDto
            (Profile profile) {
        if (profile == null) {
            return null;
        }

        return OwnerProfileDto.builder()
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .dob(profile.getDob())
                .city(profile.getCity())
                .address(profile.getAddress())
                .mobile(profile.getMobile())
                .email(profile.getEmail())
                .linkedInUrl(profile.getLinkedInUrl())
                .fbUrl(profile.getFbUrl())
                .githubUrl(profile.getGithubUrl())
                .instaUrl(profile.getInstaUrl())
                .build();
    }

    // OwnerProfileDto -> Profile
    public static Profile mapOwnerProfileDtoToProfile
    (OwnerProfileDto ownerProfileDto) {
        if (ownerProfileDto == null) {
            return null;
        }

        return Profile.builder()
                .firstName(ownerProfileDto.getFirstName())
                .lastName(ownerProfileDto.getLastName())
                .dob(ownerProfileDto.getDob())
                .city(ownerProfileDto.getCity())
                .address(ownerProfileDto.getAddress())
                .mobile(ownerProfileDto.getMobile())
                .email(ownerProfileDto.getEmail())
                .linkedInUrl(ownerProfileDto.getLinkedInUrl())
                .fbUrl(ownerProfileDto.getFbUrl())
                .githubUrl(ownerProfileDto.getGithubUrl())
                .instaUrl(ownerProfileDto.getInstaUrl())
                .build();
    }
}

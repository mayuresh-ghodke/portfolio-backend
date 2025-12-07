package com.portfolio.service;

import com.portfolio.dto.OwnerProfileDto;
import com.portfolio.dto.ProfileDto;

public interface ProfileService {

    ProfileDto saveProfile(ProfileDto profileDto);
    ProfileDto getMyProfile();

    ProfileDto updateProfile(ProfileDto profileDto);

    boolean deleteMyProfile();

    OwnerProfileDto getOwnerProfile();

    boolean updatePassword(String email, String newPassword);

}

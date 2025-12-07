package com.portfolio.repository;

import com.portfolio.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Optional<Profile> findByEmail(String email);

    Profile findByUserId(int userId);
}

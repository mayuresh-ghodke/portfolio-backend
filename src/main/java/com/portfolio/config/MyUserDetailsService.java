package com.portfolio.config;

import com.portfolio.model.Profile;
import com.portfolio.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    public MyUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Profile profile = profileRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found with : " + username));

        return User.builder()
                .username(profile.getEmail())
                .password(profile.getPassword())
                .roles(profile.getRoleName())
                .build();
    }
}

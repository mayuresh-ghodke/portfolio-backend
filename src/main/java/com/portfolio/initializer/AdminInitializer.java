package com.portfolio.initializer;

import com.portfolio.model.Profile;
import com.portfolio.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Value("${admin.default.email}")
    private String ADMIN_EMAIL;

    @Value("${admin.default.password}")
    private String ADMIN_PASSWORD;

    @Value("${admin.default.roleName}")
    private String ROLE_NAME;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(profileRepository.count() == 0){
            Profile profile = new Profile();
            profile.setEmail(ADMIN_EMAIL);
            profile.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
            profile.setRoleName(ROLE_NAME);
            Profile saved = profileRepository.save(profile);

            System.out.println("Admin created using application properties.: " + saved);
        }
    }
}

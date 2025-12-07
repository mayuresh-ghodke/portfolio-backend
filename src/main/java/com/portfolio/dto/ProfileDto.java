package com.portfolio.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {

    private String firstName;
    private String lastName;
    private String fathersName;
    private String dob;
    private String city;
    private String address;
    private String mobile;
    private String email;
    private String password;

    // social links
    private String linkedInUrl;
    private String githubUrl;
    private String instaUrl;
    private String fbUrl;
}

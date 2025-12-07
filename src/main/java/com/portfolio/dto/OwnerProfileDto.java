package com.portfolio.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerProfileDto {

    private String firstName;
    private String lastName;
    private String dob;
    private String city;
    private String address;
    private String mobile;
    private String email;

    // social links
    private String linkedInUrl;
    private String githubUrl;
    private String instaUrl;
    private String fbUrl;
}

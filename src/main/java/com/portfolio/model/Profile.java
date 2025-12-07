package com.portfolio.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profile")
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String firstName;
    private String lastName;
    private String fathersName;
    private String dob;
    private String city;
    private String address;
    private String mobile;
    private String email;
    private String password;

    private String roleName;

    // social links
    private String linkedInUrl;
    private String githubUrl;
    private String instaUrl;
    private String fbUrl;
}
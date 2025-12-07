package com.portfolio.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expId;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String roleName;

    @Column(nullable = true)
    private String location;

    @Column(nullable = false)
    private LocalDate startedIn;

    // endedOn can be null for "currently working"
    @Column(nullable = true)
    private LocalDate endedOn;

    @Column(nullable = true)
    private String workMode;

    @Column(length = 1000, nullable = true)
    private String description;

    @Column(nullable = true)
    private String jobType;

    @Column(nullable = false)
    private boolean hidden;
}


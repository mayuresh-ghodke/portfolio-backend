package com.portfolio.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int projectId;
    private String name;
    private String subtitle;
    private String startDate;
    private String endDate;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> images;

    private String info;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tech;

    private String github;
    private String link;
    private boolean hidden;
}
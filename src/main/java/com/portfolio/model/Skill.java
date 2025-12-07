package com.portfolio.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String skillName;

    private String iconName; // e.g. "FaReact"

    @ManyToOne
    @JoinColumn(name = "category_id")
    private SkillCategory category;
}

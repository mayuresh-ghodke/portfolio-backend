package com.portfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "about")
public class AboutInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aboutId;

    private String aboutBio;

    @Lob // store long HTML content
    @Column(columnDefinition = "LONGTEXT")
    private String aboutInfo;

    private String aboutTitle;

    @Column(nullable = true)
    private String imgUrl;
}

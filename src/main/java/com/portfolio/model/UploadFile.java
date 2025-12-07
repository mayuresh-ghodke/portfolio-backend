package com.portfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_files")
public class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fileId;

    private String fileName;
    private String fileType;
    private String description;
    private boolean hidden;
}

package com.portfolio.service;

import com.portfolio.model.Project;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectService {

    Project saveProject(Project project, List<MultipartFile> images);
    List<Project> getAllProjects();
    Project getProjectById(int projectId);
    Project getProjectByName(String projectName);
    boolean deleteProject(int projectId);
    Project updateProject(Project project, int projectId);
}

package com.portfolio.service.serviceimpl;

import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.model.Project;
import com.portfolio.repository.ProjectRepository;
import com.portfolio.service.FileStorageService;
import com.portfolio.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public Project saveProject(Project project, List<MultipartFile> images) {
        List<String> imagePaths = new ArrayList<>();

        try {
            if (images != null) {
                for (MultipartFile file : images) {

                    String fileName = fileStorageService.saveFile(file);

                    // 4. Add path to list
                    imagePaths.add(fileName);
                }
            }

            project.setImages(imagePaths);

            return projectRepository.save(project);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("File save failed");
        }
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(int projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(()->new ResourceNotFoundException("Project not found with id: "+projectId));
    }

    @Override
    public Project getProjectByName(String projectName) {
        Project project = projectRepository.findByName(projectName);
        if(project == null){
            throw new ResourceNotFoundException("Project not found with name: "+projectName);
        }
        else
            return project;
    }

    @Override
    public boolean deleteProject(int projectId) {
        Project project = getProjectById(projectId);
        projectRepository.delete(project);
        return true;
    }

    @Override
    public Project updateProject(Project project, int projectId) {
        Project existing = getProjectById(projectId);
        existing.setName(project.getName());
        existing.setSubtitle(project.getSubtitle());
        existing.setStartDate(project.getStartDate());
        existing.setEndDate(project.getEndDate());
        existing.setImages(project.getImages());
        existing.setInfo(project.getInfo());
        existing.setLink(project.getLink());
        existing.setGithub(project.getGithub());
        existing.setHidden(project.isHidden());
        existing.setTech(project.getTech());
        return projectRepository.save(existing);
    }
}

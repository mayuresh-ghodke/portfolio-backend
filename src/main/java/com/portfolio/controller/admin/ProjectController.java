package com.portfolio.controller.admin;

import com.portfolio.model.Project;
import com.portfolio.service.ProjectService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProject(
            @RequestParam("name") String name,
            @RequestParam("subtitle") String subtitle,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("info") String info,
            @RequestParam("github") String github,
            @RequestParam("link") String link,
            @RequestParam("hidden") boolean hidden,
            @RequestParam(value = "tech", required = false) List<String> tech,
            @RequestParam(value = "images", required = false) List<MultipartFile> images
    ) {
        try {
            Project project = new Project();
            project.setName(name);
            project.setSubtitle(subtitle);
            project.setStartDate(startDate);
            project.setEndDate(endDate);
            project.setInfo(info);
            project.setGithub(github);
            project.setLink(link);
            project.setHidden(hidden);
            project.setTech(tech);

            Project saved = projectService.saveProject(project, images);

            return new ResponseEntity<>(saved, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error saving project", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get/all")
    public ResponseEntity<List<Project>> getAllProjects(){
        List<Project> list = projectService.getAllProjects();
        if(list.isEmpty()){
            return new ResponseEntity<>(List.of(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") int projectId){
        Project project = projectService.getProjectById(projectId);
        if(project == null)
            return null;
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Project> updateProject(@RequestBody Project newProject,
                                                 @PathVariable("id") int projectId){
        Project updated = projectService.updateProject(newProject, projectId);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProject(@PathVariable("id") int projectId){
        boolean result = projectService.deleteProject(projectId);
        if(result)
            return new ResponseEntity<>(true, HttpStatus.OK);
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

}

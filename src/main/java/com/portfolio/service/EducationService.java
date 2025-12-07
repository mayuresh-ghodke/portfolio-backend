package com.portfolio.service;

import com.portfolio.model.Education;

import java.util.List;

public interface EducationService {

    Education saveEducation(Education education);
    List<Education> getAllEducation();
    Education updateEducation(int eduId, Education education);
    boolean deleteEducation(int eduId);
    Education getEducationById(int eduId);
}

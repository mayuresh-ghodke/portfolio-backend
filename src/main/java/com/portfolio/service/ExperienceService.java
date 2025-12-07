package com.portfolio.service;

import com.portfolio.model.Experience;

import java.util.List;

public interface ExperienceService {

    Experience saveExperience(Experience experience);
    List<Experience> getAllExperience();
    Experience updateExperience(int expId, Experience experience);
    Experience deleteExperience(int expId);
    Experience getExperienceById(int expId);

}

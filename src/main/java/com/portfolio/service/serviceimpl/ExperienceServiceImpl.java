package com.portfolio.service.serviceimpl;

import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.model.Experience;
import com.portfolio.repository.ExperienceRepository;
import com.portfolio.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    @Autowired
    private ExperienceRepository experienceRepository;

    @Override
    public Experience saveExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    @Override
    public List<Experience> getAllExperience() {
        return experienceRepository.findAll();
    }

    @Override
    public Experience updateExperience(int expId, Experience experience) {
        Experience existing = experienceRepository.findById(expId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found with ID: " + expId));

        existing.setCompanyName(experience.getCompanyName());
        existing.setRoleName(experience.getRoleName());
        existing.setLocation(experience.getLocation());
        existing.setStartedIn(experience.getStartedIn());
        existing.setEndedOn(experience.getEndedOn());
        existing.setWorkMode(experience.getWorkMode());
        existing.setDescription(experience.getDescription());
        existing.setJobType(experience.getJobType());
        existing.setHidden(experience.isHidden());

        return experienceRepository.save(existing);
    }

    @Override
    public Experience deleteExperience(int expId) {
        Experience existing = experienceRepository.findById(expId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found with ID: " + expId));

        experienceRepository.delete(existing);
        return existing;
    }

    @Override
    public Experience getExperienceById(int expId) {
        return experienceRepository.findById(expId)
                .orElseThrow(() -> new ResourceNotFoundException("Experience not found with ID: " + expId));
    }
}


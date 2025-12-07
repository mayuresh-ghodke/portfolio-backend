package com.portfolio.service.serviceimpl;

import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.model.Education;
import com.portfolio.repository.EducationRepository;
import com.portfolio.service.EducationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationRepository educationRepository;

    @Override
    public Education saveEducation(Education education) {
        if(education != null)
            return educationRepository.save(education);
        return null;
    }

    @Override
    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }

    @Override
    public Education updateEducation(int eduId, Education education) {
       Education existing = getEducationById(eduId);
       if(existing != null){
           existing.setSchoolName(education.getSchoolName());
           existing.setDegreeName(education.getDegreeName());
           existing.setPlace(education.getPlace());
           existing.setUniversityName(education.getUniversityName());
           existing.setPassingYear(education.getPassingYear());
       }
       return educationRepository.save(existing);
    }

    @Override
    public boolean deleteEducation(int eduId) {
        if(getEducationById(eduId) != null){
            educationRepository.deleteById(eduId);
            return true;
        }
        return false;
    }

    @Override
    public Education getEducationById(int eduId) {
        Education existingEdu = educationRepository.findById(eduId)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found with ID: " + eduId));
        return existingEdu;
    }
}

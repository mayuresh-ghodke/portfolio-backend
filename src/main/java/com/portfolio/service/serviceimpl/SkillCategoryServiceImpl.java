package com.portfolio.service.serviceimpl;

import com.portfolio.model.SkillCategory;
import com.portfolio.repository.SkillCategoryRepository;
import com.portfolio.service.SkillCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillCategoryServiceImpl implements SkillCategoryService {

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    @Override
    public SkillCategory saveSkillCategory(SkillCategory skillCategory) {

        return skillCategoryRepository.save(skillCategory);
    }

    @Override
    public List<SkillCategory> getAllSkillCategories() {
        return skillCategoryRepository.findAll();
    }
}

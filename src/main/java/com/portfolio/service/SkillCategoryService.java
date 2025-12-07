package com.portfolio.service;

import com.portfolio.model.SkillCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillCategoryService {

    SkillCategory saveSkillCategory(SkillCategory skillCategory);
    List<SkillCategory> getAllSkillCategories();
}

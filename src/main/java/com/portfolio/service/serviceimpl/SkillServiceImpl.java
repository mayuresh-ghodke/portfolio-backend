package com.portfolio.service.serviceimpl;

import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.model.Skill;
import com.portfolio.model.SkillCategory;
import com.portfolio.repository.SkillCategoryRepository;
import com.portfolio.repository.SkillRepository;
import com.portfolio.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.spi.ServiceRegistry;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private SkillRepository skillRepository;

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    SkillServiceImpl(SkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill saveSkill(Skill skill, int categoryId) {
        SkillCategory category = skillCategoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found with ID-"+categoryId));
        skill.setCategory(category);
        return skillRepository.save(skill);
    }

    @Override
    public Skill updateSkill(int skillId, Skill skill, int categoryId) {
        Skill existing = getSkillBySkillId(skillId);
        SkillCategory category = skillCategoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found with ID-"+categoryId));
        skill.setCategory(category);
        if(existing != null) {
            existing.setSkillName(skill.getSkillName());
            existing.setIconName(skill.getIconName());
            existing.setCategory(category);
            return skillRepository.save(existing);
        }
        return null;
    }

    @Override
    public boolean deleteSkill(int skillId) {
        Skill skill = getSkillBySkillId(skillId);
        if(skill != null){
            skillRepository.deleteById(skillId);
            return true;
        }
        return false;
    }

    @Override
    public Skill getSkillBySkillId(int skillId) {
        return skillRepository.findById(skillId)
                .orElseThrow(()-> new ResourceNotFoundException("Skill not found with id: " + skillId));
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

}

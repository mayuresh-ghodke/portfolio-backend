package com.portfolio.service;

import com.portfolio.model.Skill;

import java.util.List;

public interface SkillService {

    Skill saveSkill(Skill skill, int categoryId);
    List<Skill> getAllSkills();
    Skill updateSkill(int skillId, Skill skill, int categoryId);
    boolean deleteSkill(int skillId);
    Skill getSkillBySkillId(int skillId);
}

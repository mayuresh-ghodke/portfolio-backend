package com.portfolio.repository;

import com.portfolio.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

    Skill findBySkillName(String skillName);

    void deleteBySkillName(String skillName);

    @Query("SELECT s.id FROM Skill s WHERE s.skillName = :skillName")
    Integer findSkillIdBySkillName(@Param("skillName") String skillName);

    List<Skill> findByCategoryId(int categoryId);
}


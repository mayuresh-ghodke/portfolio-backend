package com.portfolio.controller.admin;

import com.portfolio.model.Skill;
import com.portfolio.model.SkillCategory;
import com.portfolio.service.SkillCategoryService;
import com.portfolio.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/skill")
@CrossOrigin(origins = "http://localhost:3000")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillCategoryService categoryService;

    // ================== CATEGORY APIs ==================

    // Add new category
    @PostMapping("/categories/save")
    public ResponseEntity<SkillCategory> addCategory(@RequestBody SkillCategory category) {
        return new ResponseEntity<>(categoryService.saveSkillCategory(category), HttpStatus.CREATED);
    }

    // Get all categories
    @GetMapping("/categories")
    public ResponseEntity<List<SkillCategory>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllSkillCategories());
    }

    // ==================== SKILL APIs =====================

    // Create skill with categoryId
    @PostMapping("/save")
    public ResponseEntity<Skill> addSkill(
            @RequestBody Skill skill,
            @RequestParam int categoryId
    ) {
        Skill savedSkill = skillService.saveSkill(skill, categoryId);
        return new ResponseEntity<>(savedSkill, HttpStatus.CREATED);
    }

    // Get all skills
    @GetMapping("/all")
    public ResponseEntity<List<Skill>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    // Update skill
    @PutMapping("/update/{skillId}")
    public ResponseEntity<Skill> updateSkill(
            @PathVariable int skillId,
            @RequestBody Skill skill,
            @RequestParam int categoryId
    ) {
        Skill existing = skillService.getSkillBySkillId(skillId);

        if (existing == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Skill updated = skillService.updateSkill(skillId, skill, categoryId);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // Delete skill
    @DeleteMapping("/delete/{skillId}")
    public ResponseEntity<?> deleteSkill(@PathVariable int skillId) {
        boolean result = skillService.deleteSkill(skillId);

        if (result)
            return ResponseEntity.ok("Skill deleted successfully");

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Skill not found");
    }

    // Get skills by category name
    @GetMapping("/get/byCategory/{categoryName}")
    public ResponseEntity<List<Skill>> getSkillsByCategory(
            @PathVariable String categoryName
    ) {
        List<Skill> skills = skillService.getAllSkills().stream()
                .filter(s -> s.getCategory().getCategoryName().equalsIgnoreCase(categoryName))
                .toList();

        return ResponseEntity.ok(skills);
    }
}

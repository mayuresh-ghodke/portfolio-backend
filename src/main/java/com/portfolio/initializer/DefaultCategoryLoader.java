package com.portfolio.initializer;

import com.portfolio.model.SkillCategory;
import com.portfolio.repository.SkillCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DefaultCategoryLoader implements CommandLineRunner {

    private final SkillCategoryRepository categoryRepository;

    public DefaultCategoryLoader(SkillCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {

        // If categories already exist, do nothing
        if (categoryRepository.count() > 0) {
            return;
        }

        List<String> defaultCategories = Arrays.asList(
                "Frontend",
                "Backend",
                "DevOps",
                "Database",
                "Tools"
        );

        for (String name : defaultCategories) {
            SkillCategory category = new SkillCategory();
            category.setCategoryName(name);
            categoryRepository.save(category);
        }

        System.out.println("Default Skill Categories Loaded.");
    }
}

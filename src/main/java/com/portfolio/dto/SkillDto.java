package com.portfolio.dto;

import com.portfolio.model.SkillCategory;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkillDto {

    private int skillId;
    private String skillName;
    private String iconName;
    private SkillCategory category;
}

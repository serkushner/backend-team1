package com.exadel.project.internship.mapper;

import com.exadel.project.internship.dto.SkillDTO;
import com.exadel.project.internship.dto.SubjectDTO;
import com.exadel.project.internship.entity.Subject;
import com.exadel.project.skill.entity.Skill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    SkillDTO entityToDto(Skill entity);

    Skill dtoToEntity(SkillDTO dto);
}

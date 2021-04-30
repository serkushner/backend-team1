package com.exadel.project.skill.mapper;

import com.exadel.project.skill.dto.SkillDTO;
import com.exadel.project.skill.entity.Skill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    SkillDTO entityToDto(Skill skill);

    Skill dtoToEntity(SkillDTO dto);
}

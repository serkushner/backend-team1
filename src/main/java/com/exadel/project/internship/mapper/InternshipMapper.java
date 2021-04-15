package com.exadel.project.internship.mapper;

import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.skill.mapper.SkillMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {CountryMapper.class, SubjectMapper.class,
                SkillMapper.class, InternshipTypeMapper.class})
public interface InternshipMapper {

    InternshipDTO entityToDto(Internship entity);
}
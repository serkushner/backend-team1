package com.exadel.project.internship.mapper;

import com.exadel.project.InternshipType.entity.InternshipType;
import com.exadel.project.common.utils.MapperUtil;
import com.exadel.project.country.entity.Country;
import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.subject.entity.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {CountryMapper.class, SubjectMapper.class,
                SkillMapper.class, InternshipTypeMapper.class})
public interface InternshipMapper {

    InternshipDTO entityToDto(Internship entity);
}
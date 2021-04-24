package com.exadel.project.internship.mapper;

import com.exadel.project.InternshipType.entity.InternshipType;
import com.exadel.project.common.utils.MapperUtil;
import com.exadel.project.country.entity.Country;
import com.exadel.project.internship.dto.InternshipDetailsDTO;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.skill.entity.Skill;
import com.exadel.project.subject.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring",
        uses = {CountryMapper.class, SubjectMapper.class,
                SkillMapper.class, InternshipTypeMapper.class})
public interface InternshipDetailsMapper {

    @Mapping(target = "countries", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "internshipType", ignore = true)
    @Mapping(target = "id", ignore = true)
    Internship dtoToEntity(InternshipDetailsDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateInternship(InternshipDetailsDTO internshipDetailsDTO,
                          @MappingTarget Internship internship);
}

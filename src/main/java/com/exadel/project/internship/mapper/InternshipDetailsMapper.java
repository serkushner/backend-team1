package com.exadel.project.internship.mapper;

import com.exadel.project.internship.dto.InternshipDetailsDTO;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.skill.mapper.SkillMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring",
        uses = {CountryMapper.class, SubjectMapper.class,
                SkillMapper.class, InternshipTypeMapper.class})
public interface InternshipDetailsMapper {

    InternshipDetailsDTO entityToDto(Internship entity);

    Internship dtoToEntity(InternshipDetailsDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateInternship(InternshipDetailsDTO internshipDetailsDTO,
                          @MappingTarget Internship internship);
}

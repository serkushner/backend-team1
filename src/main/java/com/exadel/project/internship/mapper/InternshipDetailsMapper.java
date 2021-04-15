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

    @Mapping(source = "additionalInfoInternship",
            target = "additionalInfo")
    InternshipDetailsDTO entityToDto(Internship entity);

    @Mapping(source = "additionalInfo", target = "additionalInfoInternship")
    Internship dtoToEntity(InternshipDetailsDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "additionalInfo", target = "additionalInfoInternship")
    void updateInternship(InternshipDetailsDTO internshipDetailsDTO,
                          @MappingTarget Internship internship);
}

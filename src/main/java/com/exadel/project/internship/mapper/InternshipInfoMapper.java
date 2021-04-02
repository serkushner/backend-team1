package com.exadel.project.internship.mapper;

import com.exadel.project.internship.dto.InternshipInfoDTO;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.entity.InternshipType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SubjectMapper.class,SkillMapper.class,InternshipTypeMapper.class,CountryMapper.class})

public interface InternshipInfoMapper {

    InternshipInfoDTO entityToDTO(Internship entity);

    Internship dtoToEntity(InternshipInfoDTO dto);


}

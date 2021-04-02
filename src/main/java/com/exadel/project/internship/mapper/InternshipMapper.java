package com.exadel.project.internship.mapper;

import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.entity.Internship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SubjectMapper.class, CountryMapper.class})
public interface InternshipMapper {

    InternshipDTO entityToDto(Internship entity);

    Internship dtoToEntity(InternshipDTO dto);


}

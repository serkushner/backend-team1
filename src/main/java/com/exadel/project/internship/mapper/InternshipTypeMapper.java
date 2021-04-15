package com.exadel.project.internship.mapper;

import com.exadel.project.internship.dto.InternshipTypeDTO;
import com.exadel.project.internship.entity.InternshipType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InternshipTypeMapper {

    InternshipType dtoToEntity(InternshipTypeDTO dto);

    InternshipTypeDTO entityToDto(InternshipType internshipType);
}

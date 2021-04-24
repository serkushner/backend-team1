package com.exadel.project.InternshipType.mapper;

import com.exadel.project.InternshipType.dto.InternshipTypeDTO;
import com.exadel.project.InternshipType.entity.InternshipType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InternshipTypeMapper {

    InternshipType dtoToEntity(InternshipTypeDTO dto);

    InternshipTypeDTO entityToDto(InternshipType internshipType);
}

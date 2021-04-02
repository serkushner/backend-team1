package com.exadel.project.internship.mapper;

import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.dto.InternshipTypeDTO;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.entity.InternshipType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InternshipTypeMapper {
    InternshipTypeDTO entityToDto(InternshipType entity);

    InternshipType dtoToEntity(InternshipTypeDTO dto);
}


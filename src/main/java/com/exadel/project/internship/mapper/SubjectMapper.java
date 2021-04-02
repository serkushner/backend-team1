package com.exadel.project.internship.mapper;

import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.dto.SubjectDTO;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.entity.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectDTO entityToDto(Subject entity);

    Subject dtoToEntity(SubjectDTO dto);
}

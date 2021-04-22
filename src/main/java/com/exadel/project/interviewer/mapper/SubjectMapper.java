package com.exadel.project.interviewer.mapper;

import com.exadel.project.internship.entity.Subject;
import com.exadel.project.interviewer.dto.SubjectDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectDTO entityToDto(Subject entity);

    Subject dtoToEntity(SubjectDTO dto);


}

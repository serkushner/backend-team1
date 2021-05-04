package com.exadel.project.subject.mapper;

import com.exadel.project.subject.dto.SubjectDTO;
import com.exadel.project.subject.entity.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectDTO entityToDto(Subject subject);

    Subject dtoToEntity(SubjectDTO dto);
}

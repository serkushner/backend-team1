package com.exadel.project.interview.mapper;

import com.exadel.project.interview.dto.InterviewTimeDTO;
import com.exadel.project.interview.entity.InterviewTime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InterviewTimeMapper {

    InterviewTimeDTO entityToDto(InterviewTime entity);

    InterviewTime dtoToEntity(InterviewTimeDTO dto);
}

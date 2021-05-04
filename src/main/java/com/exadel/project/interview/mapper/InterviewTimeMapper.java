package com.exadel.project.interview.mapper;

import com.exadel.project.interview.dto.InterviewTimeRequestDTO;
import com.exadel.project.interview.dto.InterviewTimeResponseDTO;
import com.exadel.project.interview.entity.InterviewTime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InterviewTimeMapper {

    InterviewTimeResponseDTO entityToDto(InterviewTime entity);

    InterviewTime dtoToEntity(InterviewTimeResponseDTO dto);

    InterviewTime dtoToEntity(InterviewTimeRequestDTO dto);
}

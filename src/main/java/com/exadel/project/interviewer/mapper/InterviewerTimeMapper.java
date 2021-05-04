package com.exadel.project.interviewer.mapper;

import com.exadel.project.interviewer.dto.InterviewTimeDTO;
import com.exadel.project.interviewer.entity.InterviewerTime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = InterviewerAppointmentMapper.class)
public interface InterviewerTimeMapper {
    InterviewerTime dtoToEntity(InterviewTimeDTO dto);

    InterviewTimeDTO entityToDto(InterviewerTime entity);
}

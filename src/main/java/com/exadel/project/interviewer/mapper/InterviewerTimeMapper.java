package com.exadel.project.interviewer.mapper;

import com.exadel.project.interview.dto.InterviewTimeAppointmentDTO;
import com.exadel.project.interviewer.entity.InterviewerTime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = InterviewerAppointmentMapper.class)
public interface InterviewerTimeMapper {
    InterviewerTime dtoToEntity(InterviewTimeAppointmentDTO dto);

    InterviewTimeAppointmentDTO entityToDto(InterviewerTime entity);
}

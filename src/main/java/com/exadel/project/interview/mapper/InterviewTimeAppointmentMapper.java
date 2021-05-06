package com.exadel.project.interview.mapper;

import com.exadel.project.interview.dto.InterviewTimeAppointmentDTO;
import com.exadel.project.interview.entity.InterviewTime;
import com.exadel.project.interviewer.entity.InterviewerTime;
import com.exadel.project.interviewer.mapper.InterviewerAppointmentMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = InterviewerAppointmentMapper.class)
public interface InterviewTimeAppointmentMapper {
    InterviewerTime dtoToEntity(InterviewTimeAppointmentDTO dto);

    InterviewTimeAppointmentDTO entityToDto(InterviewTime entity);
}

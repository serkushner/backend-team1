package com.exadel.project.interviewer.mapper;

import com.exadel.project.interview.mapper.InterviewTimeMapper;
import com.exadel.project.interviewer.dto.InterviewerAppointmentDTO;
import com.exadel.project.interviewer.entity.Interviewer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = InterviewTimeMapper.class)
public interface InterviewerAppointmentMapper {

    InterviewerAppointmentDTO entityToDto(Interviewer interviewer);

    Interviewer dtoToEntity(InterviewerAppointmentDTO interviewerAppointmentDTO);
}

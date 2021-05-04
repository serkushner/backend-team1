package com.exadel.project.interviewer.mapper;

import com.exadel.project.interview.mapper.InterviewTimeMapper;
import com.exadel.project.interviewer.dto.InterviewerAppointmentDTO;
import com.exadel.project.interviewer.entity.Interviewer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InterviewerAppointmentMapper {
    @Mapping(target = "interviewerId", source = "interviewer.id")
    InterviewerAppointmentDTO entityToDto(Interviewer interviewer);

    @Mapping(target = "id", source = "interviewerAppointmentDTO.interviewerId")
    Interviewer dtoToEntity(InterviewerAppointmentDTO interviewerAppointmentDTO);
}

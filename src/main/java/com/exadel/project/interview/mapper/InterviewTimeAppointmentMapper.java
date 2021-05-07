package com.exadel.project.interview.mapper;

import com.exadel.project.interview.dto.InterviewTimeAppointmentDTO;
import com.exadel.project.interview.entity.InterviewTime;
import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.interviewer.entity.InterviewerTime;
import com.exadel.project.interviewer.mapper.InterviewerAppointmentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = InterviewerAppointmentMapper.class)
public interface InterviewTimeAppointmentMapper {

    InterviewerTime dtoToEntity(InterviewTimeAppointmentDTO dto);

    @Mapping(source = "entity.id",target = "interviewTimeId")
    @Mapping(source = "interviewersList", target = "interviewers")
    InterviewTimeAppointmentDTO entityToDto(InterviewTime entity, List<Interviewer> interviewersList);
}

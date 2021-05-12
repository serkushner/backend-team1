package com.exadel.project.interview.mapper;

import com.exadel.project.interview.dto.InterviewToInterviewerDTO;
import com.exadel.project.interview.dto.InterviewToTraineeDTO;
import com.exadel.project.interview.entity.Interview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {InterviewTimeMapper.class})
public interface InterviewToTraineeMapper {

    @Mapping(target = "interviewerId", expression = "java(entity.getInterviewer().getId())")
    @Mapping(target = "internshipId", expression = "java(entity.getInternship().getId())")
    InterviewToTraineeDTO entityToDto(Interview entity);

    Interview dtoToEntity(InterviewToInterviewerDTO dto);
}


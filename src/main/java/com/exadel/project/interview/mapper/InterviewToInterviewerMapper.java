package com.exadel.project.interview.mapper;

import com.exadel.project.internship.mapper.InternshipMapper;
import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.dto.InterviewToInterviewerDTO;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.interviewer.mapper.InterviewerMapper;
import com.exadel.project.trainee.mapper.TraineeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {InterviewTimeMapper.class})
public interface InterviewToInterviewerMapper {
    @Mapping(target = "traineeId", expression = "java(entity.getTrainee().getId())")
    @Mapping(target = "internshipId", expression = "java(entity.getInternship().getId())")
    InterviewToInterviewerDTO entityToDto(Interview entity);

    Interview dtoToEntity(InterviewToInterviewerDTO dto);
}

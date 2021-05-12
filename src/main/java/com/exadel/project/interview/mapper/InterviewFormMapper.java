package com.exadel.project.interview.mapper;

import com.exadel.project.interview.dto.InterviewFormDTO;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.trainee.entity.AdditionalInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InterviewFormMapper {

    @Mapping(target = "subscription", expression = "java(interview.getName())")
    @Mapping(target = "traineeName", expression = "java(interview.getTrainee().getName())")
    @Mapping(target = "traineeSurname", expression = "java(interview.getTrainee().getSurname())")
    @Mapping(target = "interviewTime", expression = "java(interview.getInterviewTime().getStartDate())")
    @Mapping(target = "interviewerName", expression = "java(interview.getInterviewer().getName())")
    @Mapping(target = "interviewerSurname", expression = "java(interview.getInterviewer().getSurname())")
    @Mapping(target = "id", expression = "java(interview.getId())")
    InterviewFormDTO entityToDto(Interview interview, AdditionalInfo additionalInfo);
}

package com.exadel.project.internship.mapper;


import com.exadel.project.internship.dto.InterviewerDto;
import com.exadel.project.internship.entity.Interviewer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InterviewerMapper {

    InterviewerDto entityToDto(Interviewer entity);

    Interviewer dtoToEntity(InterviewerDto dto);

}

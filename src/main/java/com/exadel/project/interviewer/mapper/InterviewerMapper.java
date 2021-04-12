package com.exadel.project.interviewer.mapper;


import com.exadel.project.interviewer.dto.InterviewerDto;
import com.exadel.project.interviewer.entity.Interviewer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InterviewerMapper {

    InterviewerDto entityToDto(Interviewer entity);

    Interviewer dtoToEntity(InterviewerDto dto);

}

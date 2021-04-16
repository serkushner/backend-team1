package com.exadel.project.interviewer.mapper;


import com.exadel.project.interview.mapper.InterviewMapper;
import com.exadel.project.interviewer.dto.InterviewerDTO;
import com.exadel.project.interviewer.entity.Interviewer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = InterviewMapper.class)
public interface InterviewerMapper {

    InterviewerDTO entityToDto(Interviewer entity);

    Interviewer dtoToEntity(InterviewerDTO dto);

}

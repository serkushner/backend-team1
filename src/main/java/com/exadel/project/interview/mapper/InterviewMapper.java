package com.exadel.project.interview.mapper;


import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.entity.Interview;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {InterviewTimeMapper.class})
public interface InterviewMapper {

    InterviewDTO entityToDto(Interview entity);

    Interview dtoToEntity(InterviewDTO dto);
}

package com.exadel.project.interview.mapper;


import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.mapper.InternshipMapper;
import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.trainee.mapper.TraineeMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {InterviewTimeMapper.class, TraineeMapper.class, InternshipMapper.class})
public interface InterviewMapper {

    InterviewDTO entityToDto(Interview entity);

    Interview dtoToEntity(InterviewDTO dto);
}

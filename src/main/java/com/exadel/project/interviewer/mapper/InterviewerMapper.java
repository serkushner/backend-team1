package com.exadel.project.interviewer.mapper;

import com.exadel.project.common.utils.MapperUtil;
import com.exadel.project.interview.mapper.InterviewMapper;
import com.exadel.project.interviewer.dto.InterviewerRequestDTO;
import com.exadel.project.interviewer.dto.InterviewerResponseDTO;
import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.subject.entity.Subject;
import com.exadel.project.subject.mapper.SubjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InterviewMapper.class, SubjectMapper.class})
public interface InterviewerMapper {

    @Mapping(target = "subjects", expression = "java(getSubjectsName(entity.getSubjects()))")
    InterviewerResponseDTO entityToDto(Interviewer entity);

    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "interviewTimes", ignore = true)
    Interviewer dtoToEntity(InterviewerRequestDTO dto);

    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "interviewTimes", ignore = true)
    Interviewer dtoToEntity(InterviewerResponseDTO dto);

    default List<String> getSubjectsName(List<Subject> subjects){
        return MapperUtil.getStrings(subjects, Subject::getName);
    }
}

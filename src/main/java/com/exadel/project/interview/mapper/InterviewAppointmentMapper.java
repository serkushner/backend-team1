package com.exadel.project.interview.mapper;

import com.exadel.project.internship.entity.Internship;
import com.exadel.project.interview.dto.InterviewAppointmentDTO;
import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.trainee.entity.Trainee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring", uses = InterviewTimeMapper.class)
public interface InterviewAppointmentMapper {
    @Mapping(target = "traineeId", expression = "java(getTraineeId(interview.getTrainee()))")
    @Mapping(target = "internshipId", expression = "java(getInternshipId(interview.getInternship()))")
    @Mapping(target = "interviewerId", expression = "java(getInterviewerId(interview.getInterviewer()))")
    InterviewAppointmentDTO entityToDto(Interview interview);


    default Long getTraineeId(Trainee trainee) {
        return Optional.ofNullable(trainee).map(Trainee::getId).orElse(null);
    }

    default Long getInternshipId(Internship internship) {
        return Optional.ofNullable(internship).map(Internship::getId).orElse(null);
    }

    default Long getInterviewerId(Interviewer interviewer) {
        return Optional.ofNullable(interviewer).map(Interviewer::getId).orElse(null);
    }
}

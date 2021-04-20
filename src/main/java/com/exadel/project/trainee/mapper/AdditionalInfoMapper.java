package com.exadel.project.trainee.mapper;

import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.common.utils.MapperUtil;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.entity.Subject;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.dto.TraineeToAdminDTO;
import com.exadel.project.trainee.dto.TraineeToAdminDetailsDTO;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.InterviewPeriod;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.entity.TraineeStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface AdditionalInfoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "traineeStatus", expression = "java(getTraineeStatus(dto.getTraineeStatus()))")
    AdditionalInfo dtoToEntity(TraineeDTO dto, Trainee trainee, Internship internship);

    @Mapping(target = "traineeName", expression = "java(additionalInfo.getTrainee().getName())")
    @Mapping(target = "traineeSurname", expression = "java(additionalInfo.getTrainee().getSurname())")
    @Mapping(target = "email", expression = "java(additionalInfo.getTrainee().getEmail())")
    @Mapping(target = "subjects", expression = "java(getSubjectsName(additionalInfo.getInternship().getSubjects()))")
    @Mapping(target = "adminName", expression = "java(getAdminName(additionalInfo.getTrainee().getAdministrator()))")
    @Mapping(target = "adminSurname", expression = "java(getAdminSurname(additionalInfo.getTrainee().getAdministrator()))")
    @Mapping(target = "internshipName", expression = "java(additionalInfo.getInternship().getTitle())")
    @Mapping(target = "traineeLocation", expression = "java(additionalInfo.getTrainee().getCountry().getName())")
    TraineeToAdminDTO entityToDto(AdditionalInfo additionalInfo);

    @Mapping(target = "traineeId", expression = "java(additionalInfo.getTrainee().getId())")
    @Mapping(target = "name", expression = "java(additionalInfo.getTrainee().getName())")
    @Mapping(target = "surname", expression = "java(additionalInfo.getTrainee().getSurname())")
    @Mapping(target = "phone", expression = "java(additionalInfo.getTrainee().getPhone())")
    @Mapping(target = "skype", expression = "java(additionalInfo.getTrainee().getSkype())")
    @Mapping(target = "location", expression = "java(additionalInfo.getTrainee().getCountry().getName())")
    @Mapping(target = "adminName", expression = "java(getAdminName(additionalInfo.getTrainee().getAdministrator()))")
    @Mapping(target = "adminSurname", expression = "java(getAdminSurname(additionalInfo.getTrainee().getAdministrator()))")
    @Mapping(target = "dates", expression = "java(getMapDates(additionalInfo.getTrainee().getInterviewPeriods()))")
    @Mapping(target = "internshipTitle", expression = "java(additionalInfo.getInternship().getTitle())")
    @Mapping(target = "techInterview")
    @Mapping(target = "hrInterview")
    TraineeToAdminDetailsDTO entityToDto(AdditionalInfo additionalInfo, List<Interview> interviews);

    default TraineeStatus getTraineeStatus(String traineeStatus){
        return traineeStatus == null ? TraineeStatus.REGISTERED : TraineeStatus.valueOf(traineeStatus);
    }

    default List<String> getSubjectsName(List<Subject> subjects){
        return MapperUtil.getStrings(subjects, Subject::getName);
    }

    default String getAdminName(Administrator administrator){
        return Optional.ofNullable(administrator).map(Administrator::getName).orElse(null);
    }

    default String getAdminSurname(Administrator administrator){
        return Optional.ofNullable(administrator).map(Administrator::getSurname).orElse(null);
    }

    default List<Map<String, String>> getMapDates(List<InterviewPeriod> interviewPeriods){
        return MapperUtil.getMapDates(interviewPeriods);
    }
}
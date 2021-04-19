package com.exadel.project.trainee.mapper;

import com.exadel.project.common.utils.MapperUtil;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.entity.Subject;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.dto.TraineeToAdminDTO;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.entity.TraineeStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdditionalInfoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "traineeStatus", expression = "java(getTraineeStatus(dto.getTraineeStatus()))")
    AdditionalInfo dtoToEntity(TraineeDTO dto, Trainee trainee, Internship internship);

    @Mapping(target = "traineeName", expression = "java(additionalInfo.getTrainee().getName())")
    @Mapping(target = "traineeSurname", expression = "java(additionalInfo.getTrainee().getSurname())")
    @Mapping(target = "email", expression = "java(additionalInfo.getTrainee().getEmail())")
    @Mapping(target = "subjects", expression = "java(getSubjectsName(additionalInfo.getInternship().getSubjects()))")
    TraineeToAdminDTO entityToDto(AdditionalInfo additionalInfo);

    default TraineeStatus getTraineeStatus(String traineeStatus){
        return traineeStatus == null ? TraineeStatus.REGISTERED : TraineeStatus.valueOf(traineeStatus);
    }

    default List<String> getSubjectsName(List<Subject> subjects){
        return MapperUtil.getStrings(subjects, Subject::getName);
    }
}
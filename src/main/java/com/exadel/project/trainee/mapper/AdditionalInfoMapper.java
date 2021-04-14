package com.exadel.project.trainee.mapper;

import com.exadel.project.internship.entity.Internship;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.entity.TraineeStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdditionalInfoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "traineeStatus", expression = "java(getTraineeStatus(dto.getTraineeStatus()))")
    AdditionalInfo dtoToEntity(TraineeDTO dto, Trainee trainee, Internship internship);

    default TraineeStatus getTraineeStatus(String traineeStatus){
        return traineeStatus == null ? TraineeStatus.REGISTERED : TraineeStatus.valueOf(traineeStatus);
    }
}
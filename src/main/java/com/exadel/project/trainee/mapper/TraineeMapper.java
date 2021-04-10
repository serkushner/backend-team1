package com.exadel.project.trainee.mapper;

import com.exadel.project.internship.entity.Country;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.entity.Trainee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TraineeMapper {


    //TODO: possibly error here with country
    @Mapping(target = "name", expression =
            "java(getCountryName(entity.getCountryName()))")
    TraineeDTO traineetoTraineeDTO(Trainee entity);


    default String getCountryName(Country country) {
        return country.getName();
    }

    //TODO add for traineeStatus and List<InterviewPeriodDTO> interviewPeriods
}

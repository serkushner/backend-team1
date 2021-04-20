package com.exadel.project.trainee.mapper;

import com.exadel.project.common.utils.MapperUtil;
import com.exadel.project.internship.entity.Country;
import com.exadel.project.interview.mapper.InterviewMapper;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.dto.TraineeToAdminDTO;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.InterviewPeriod;
import com.exadel.project.trainee.entity.Trainee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.*;

@Mapper(componentModel = "spring", uses = InterviewMapper.class)
public interface TraineeMapper {

    Trainee dtoToEntity(TraineeDTO dto);

    @Mapping(target = "dates", expression = "java(getMapDates(interviewPeriods))")
    @Mapping(target = "location", expression = "java(getCountryName(trainee.getCountry()))")
    @Mapping(target = "id", source = "trainee.id")
    TraineeDTO entityToDto(Trainee trainee, AdditionalInfo additionalInfo, List<InterviewPeriod> interviewPeriods);

    @Mapping(target = "id", ignore = true)
    void updateTrainee(TraineeDTO dto, @MappingTarget Trainee trainee);

    default String getCountryName(Country country) {
        return Optional.ofNullable(country).map(Country::getName).orElse(null);
    }

    default List<Map<String, String>> getMapDates(List<InterviewPeriod> interviewPeriods){
        return MapperUtil.getMapDates(interviewPeriods);
    }
}
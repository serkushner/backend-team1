package com.exadel.project.trainee.mapper;

import com.exadel.project.internship.entity.Country;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.InterviewPeriod;
import com.exadel.project.trainee.entity.Trainee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return country.getName();
    }

    default List<Map<String, String>> getMapDates(List<InterviewPeriod> interviewPeriods){
        List<Map<String, String>> dates = new ArrayList<>();
        for (InterviewPeriod interviewPeriod : interviewPeriods){
            Map<String, String> dayOfWeekToTime = new HashMap<>();
            dayOfWeekToTime.put("day", interviewPeriod.getDayOfWeek().toString());
            String startTime = interviewPeriod.getStartTime().toString();
            String endTime = interviewPeriod.getEndTime().toString();
            dayOfWeekToTime.put("time", String.join(" - ", startTime, endTime));
            dates.add(dayOfWeekToTime);
        }
        return dates;
    }
}
package com.exadel.project.trainee.mapper;

import com.exadel.project.internship.entity.Country;
import com.exadel.project.internship.service.CountryService;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.entity.Trainee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TraineeMapper {

    Trainee dtoToEntity(TraineeDTO dto);
}

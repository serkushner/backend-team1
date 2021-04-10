package com.exadel.project.trainee.mapper;

import com.exadel.project.internship.entity.Internship;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.Trainee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdditionalInfoMapper {
    @Mapping(target = "id", ignore = true)
    AdditionalInfo dtoToEntity(TraineeDTO dto, Trainee trainee, Internship internship);
}

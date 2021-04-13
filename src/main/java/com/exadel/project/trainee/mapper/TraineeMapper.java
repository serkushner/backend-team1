package com.exadel.project.trainee.mapper;

import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.entity.Trainee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TraineeMapper {

    Trainee dtoToEntity(TraineeDTO dto);

    TraineeDTO entityToDto(Trainee trainee);

    @Mapping(target = "id", ignore = true)
    void updateTrainee(TraineeDTO dto, @MappingTarget Trainee trainee);

}

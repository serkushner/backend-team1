package com.exadel.project.administrator.mapper;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.entity.Administrator;

import com.exadel.project.administrator.service.AdministratorService;
import com.exadel.project.trainee.mapper.TraineeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {PasswordEncoderMapper.class, TraineeMapper.class, AdministratorService.class})
public interface AdministratorMapper {

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    AdministratorDto entityToDto(Administrator entity);

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    Administrator dtoToEntity(AdministratorDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    void updateAdministrator(AdministratorDto dto, @MappingTarget Administrator administrator);

}

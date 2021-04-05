package com.exadel.project.administrator.mapper;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.entity.Administrator;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface AdministratorMapper {

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    AdministratorDto entityToDto(Administrator entity);

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    @Mapping(target = "role", ignore = true)
    Administrator dtoToEntity(AdministratorDto dto);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateAdministrator(AdministratorDto dto, @MappingTarget Administrator administrator);

}

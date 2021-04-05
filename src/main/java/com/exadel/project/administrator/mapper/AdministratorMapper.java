package com.exadel.project.administrator.mapper;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.entity.Administrator;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface AdministratorMapper {

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    AdministratorDto entityToDto(Administrator entity);

}

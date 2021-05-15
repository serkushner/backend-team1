package com.exadel.project.administrator.mapper;

import com.exadel.project.administrator.dto.RoleDto;
import com.exadel.project.administrator.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

        RoleDto entityToDto(Role role);

        Role dtoToEntity(RoleDto dto);
    }


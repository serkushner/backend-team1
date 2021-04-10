package com.exadel.project.trainee.mapper;

import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.trainee.dto.AdministratorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AdministratorMapper {
    @Mappings({
        @Mapping(target="name", source="entity.name"),
        @Mapping(target="surname", source="entity.surname"),
        @Mapping(target="phone", source="entity.phone"),
        @Mapping(target="skype", source="entity.skype"),
        @Mapping(target="email", source="entity.email")
    })
    AdministratorDTO administratorToAdministratorDTO(Administrator entity);

    @Mappings({
        @Mapping(target="name",  source="dto.name"),
        @Mapping(target="surname", source="dto.surname"),
        @Mapping(target="phone", source="dto.phone"),
        @Mapping(target="skype", source="dto.skype"),
        @Mapping(target="email", source="dto.email")
    })
    Administrator administratorDTOtoAdministrator(AdministratorDTO dto);
}
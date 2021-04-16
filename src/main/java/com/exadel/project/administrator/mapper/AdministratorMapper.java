package com.exadel.project.administrator.mapper;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.entity.Administrator;

import com.exadel.project.trainee.mapper.TraineeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PasswordEncoderMapper.class, TraineeMapper.class})
public interface AdministratorMapper {

    @Mapping(source = "trainees", target = "traineesDTO")
    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    AdministratorDto entityToDto(Administrator entity);

    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    //@Mapping(target = "role", ignore = true)
    Administrator dtoToEntity(AdministratorDto dto);

    //@Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "password", target = "password", qualifiedBy = EncodedMapping.class)
    void updateAdministrator(AdministratorDto dto, @MappingTarget Administrator administrator);

    default Administrator fromId(Long id) {

        if (id == null) {
            return null;
        }
        final Administrator administrator=new Administrator();
        administrator.setId(id);
        return administrator;
    }

}

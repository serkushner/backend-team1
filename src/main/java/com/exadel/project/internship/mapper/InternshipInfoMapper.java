package com.exadel.project.internship.mapper;

import com.exadel.project.internship.dto.InternshipInfoDTO;
import com.exadel.project.internship.entity.Internship;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InternshipInfoMapper {

    InternshipInfoDTO entityToDTO(Internship entity);

    Internship dtoToEntity(InternshipInfoDTO dto);


}

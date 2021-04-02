package com.exadel.project.internship.mapper;

import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.entity.Internship;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InternshipMapper {

    InternshipDTO internshipToInternshipDTO(Internship entity);

    Internship internshipDTOtoInternship(InternshipDTO dto);


}

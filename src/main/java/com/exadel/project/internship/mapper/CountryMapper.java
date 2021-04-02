package com.exadel.project.internship.mapper;

import com.exadel.project.internship.dto.CountryDTO;
import com.exadel.project.internship.dto.InternshipInfoDTO;
import com.exadel.project.internship.entity.Country;
import com.exadel.project.internship.entity.Internship;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDTO entityToDTO(Country entity);

    Country dtoToEntity(CountryDTO dto);
}

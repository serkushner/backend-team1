package com.exadel.project.internship.mapper;

import com.exadel.project.internship.dto.CountryDTO;
import com.exadel.project.internship.entity.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDTO entityToDto(Country country);

    Country dtoToEntity(CountryDTO dto);
}

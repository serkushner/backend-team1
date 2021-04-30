package com.exadel.project.country.mapper;

import com.exadel.project.country.dto.CountryDTO;
import com.exadel.project.country.entity.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryDTO entityToDto(Country country);

    Country dtoToEntity(CountryDTO dto);
}

package com.exadel.project.internship.mapper;

import com.exadel.project.common.utils.MapperUtil;
import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.entity.Country;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface InternshipMapper {
    @Mapping(target = "subjects", expression = "java(getSubjectsNames(entity.getSubjects()))")
    @Mapping(target = "countries", expression = "java(getCountriesNames(entity.getCountries()))")
    InternshipDTO entityToDto(Internship entity);


    default List<String> getSubjectsNames(List<Subject> subjects) {
        return MapperUtil.getStrings(subjects, Subject::getName);
    }


    default List<String> getCountriesNames(List<Country> countries) {
        return MapperUtil.getStrings(countries, Country::getName);
    }
}

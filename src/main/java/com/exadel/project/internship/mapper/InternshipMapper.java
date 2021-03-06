package com.exadel.project.internship.mapper;

import com.exadel.project.InternshipType.entity.InternshipType;
import com.exadel.project.common.utils.MapperUtil;
import com.exadel.project.country.entity.Country;
import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.subject.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InternshipMapper {

    @Mapping(target = "subjects", expression = "java(getSubjectsNames(entity.getSubjects()))")
    @Mapping(target = "countries", expression = "java(getCountriesNames(entity.getCountries()))")
    @Mapping(target = "internshipType", expression = "java(getInternshipType(entity.getInternshipType()))")
    @Mapping(source = "published", target = "publishedStatus")
    InternshipDTO entityToDto(Internship entity);

    default List<String> getSubjectsNames(List<Subject> subjects) {
        return MapperUtil.getStrings(subjects, Subject::getName);
    }

    default List<String> getCountriesNames(List<Country> countries) {
        return MapperUtil.getStrings(countries, Country::getName);
    }

    default String getInternshipType(InternshipType internshipType) {
        return internshipType.getType();
    }
}
package com.exadel.project.internship.mapper;

import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.entity.Country;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.entity.Subject;
import com.exadel.project.skill.entity.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface InternshipMapper {
    @Mapping(target = "subjects", expression = "java(getSubjectsNames(entity.getSubjects()))")
    @Mapping(target = "countries", expression = "java(getCountriesNames(entity.getCountries()))")
    InternshipDTO entityToDto(Internship entity);


    default List<String> getSubjectsNames(List<Subject> subjects) {
        return getStrings(subjects, Subject::getName);
    }


    default List<String> getCountriesNames(List<Country> countries) {
        return getStrings(countries, Country::getName);
    }


    default <T> List<String> getStrings(List<T> sourceList, Function<T, String> function) {
        return Optional.ofNullable(sourceList)
                .stream()
                .flatMap(Collection::stream)
                .map(function)
                .collect(Collectors.toList());
    }

}

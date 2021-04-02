package com.exadel.project.internship.mapper;

import com.exadel.project.internship.dto.InternshipInfoDTO;
import com.exadel.project.internship.entity.Country;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.entity.InternshipType;
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

public interface InternshipInfoMapper {
    @Mapping(target = "subjects", expression = "java(getSubjectsNames(entity.getSubjects()))")
    @Mapping(target = "countries", expression = "java(getCountriesNames(entity.getCountries()))")
    @Mapping(target = "internshipType", expression = "java(getInternshipType(entity.getInternshipType()))")
    @Mapping(target = "skills", expression = "java(getSkillsNames(entity.getSkills()))")
    InternshipInfoDTO entityToDto(Internship entity);


    default List<String> getSubjectsNames(List<Subject> subjects) {
        return getStrings(subjects, Subject::getName);
    }

    default List<String> getCountriesNames(List<Country> subjects) {
        return getStrings(subjects, Country::getName);
    }

    default String getInternshipType(InternshipType internshipType) {
        return internshipType.getType();
    }

    default List<String> getSkillsNames(List<Skill> skills) {
        return getStrings(skills, Skill::getName);
    }

    default <T> List<String> getStrings(List<T> sourceList, Function<T, String> function) {
        return Optional.ofNullable(sourceList)
                .stream()
                .flatMap(Collection::stream)
                .map(function)
                .collect(Collectors.toList());
    }


}

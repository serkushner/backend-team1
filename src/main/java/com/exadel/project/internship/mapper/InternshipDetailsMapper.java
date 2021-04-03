package com.exadel.project.internship.mapper;

import com.exadel.project.common.utils.MapperUtil;
import com.exadel.project.internship.dto.InternshipDetailsDTO;
import com.exadel.project.internship.entity.Country;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.entity.InternshipType;
import com.exadel.project.internship.entity.Subject;
import com.exadel.project.skill.entity.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;


@Mapper(componentModel = "spring")

public interface InternshipDetailsMapper {
    @Mapping(target = "subjects", expression = "java(getSubjectsNames(entity.getSubjects()))")
    @Mapping(target = "countries", expression = "java(getCountriesNames(entity.getCountries()))")
    @Mapping(target = "internshipType", expression = "java(getInternshipType(entity.getInternshipType()))")
    @Mapping(target = "skills", expression = "java(getSkillsNames(entity.getSkills()))")
    InternshipDetailsDTO entityToDto(Internship entity);


    default List<String> getSubjectsNames(List<Subject> subjects) {
        return MapperUtil.getStrings(subjects, Subject::getName);
    }

    default List<String> getCountriesNames(List<Country> subjects) {
        return MapperUtil.getStrings(subjects, Country::getName);
    }

    default String getInternshipType(InternshipType internshipType) {
        return internshipType.getType();
    }

    default List<String> getSkillsNames(List<Skill> skills) {
        return MapperUtil.getStrings(skills, Skill::getName);
    }
}

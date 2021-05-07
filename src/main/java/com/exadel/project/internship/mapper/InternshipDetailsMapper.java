package com.exadel.project.internship.mapper;

import com.exadel.project.InternshipType.entity.InternshipType;
import com.exadel.project.common.utils.MapperUtil;
import com.exadel.project.country.entity.Country;
import com.exadel.project.internship.dto.InternshipDetailsDTO;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.skill.entity.Skill;
import com.exadel.project.subject.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface InternshipDetailsMapper {

    @Mapping(target = "subjects", expression = "java(getSubjectsNames(entity.getSubjects()))")
    @Mapping(target = "countries", expression = "java(getCountriesNames(entity.getCountries()))")
    @Mapping(target = "internshipType", expression = "java(getInternshipType(entity.getInternshipType()))")
    @Mapping(target = "skills", expression = "java(getSkillsNames(entity.getSkills()))")
    @Mapping(source = "published", target = "publishedStatus")
    InternshipDetailsDTO entityToDto(Internship entity);

    @Mapping(target = "countries", ignore = true)
    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "internshipType", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "publishedStatus", target = "published")
    Internship dtoToEntity(InternshipDetailsDTO dto);

    default List<String> getSubjectsNames(List<Subject> subjects) {
        return MapperUtil.getStrings(subjects, Subject::getName);
    }

    default List<String> getCountriesNames(List<Country> countries) {
        return MapperUtil.getStrings(countries, Country::getName);
    }

    default String getInternshipType(InternshipType internshipType) {
        return internshipType.getType();
    }

    default List<String> getSkillsNames(List<Skill> skills) {
        return MapperUtil.getStrings(skills, Skill::getName);
    }
}

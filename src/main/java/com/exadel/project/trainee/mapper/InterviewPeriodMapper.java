package com.exadel.project.trainee.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class InterviewPeriodMapper {
    //TODO: Similarly to the article "Mapping with an abstract class", part 9
    // - https://www.baeldung.com/mapstruct
    //  Map a localTime to a time period start and time period end and vice versa for both.
    //  Plus add (if necessary) a mapper of the days_of_week to a String and in a reverse direction.
}

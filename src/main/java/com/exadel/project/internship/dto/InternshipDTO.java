package com.exadel.project.internship.dto;

import com.exadel.project.internship.entity.Country;
import com.exadel.project.internship.entity.Subject;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InternshipDTO {
    private LocalDate startDate;
    private CountryDTO country;
    private List<SubjectDTO> subjects;
    private String title;
    private String description;
}

package com.exadel.project.internship.dto;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InternshipDTO {
    private Long id;
    private LocalDate startDate;
    private List<CountryDTO> countries;
    private List<SubjectDTO> subjects;
    private String title;
    private String description;
    private InternshipTypeDTO internshipType;
    private String image;
}

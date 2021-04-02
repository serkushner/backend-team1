package com.exadel.project.internship.dto;

import com.exadel.project.internship.entity.Country;
import com.exadel.project.internship.entity.Subject;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InternshipDTO {
    private Long id;
    private LocalDate startDate;
    private List<String> countries;
    private List<String> subjects;
    private String title;
    private String description;
}

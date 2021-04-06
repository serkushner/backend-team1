package com.exadel.project.internship.dto;


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
    private String internshipType;
}

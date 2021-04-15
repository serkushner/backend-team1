package com.exadel.project.internship.dto;

import com.exadel.project.internship.entity.Format;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class InternshipDetailsDTO {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate startRequestDate;
    private LocalDate endRequestDate;
    private String description;
    private String additionalInfo;
    private String image;
    private Format format;
    private String internshipType;
    private String additionalInfoInternship;
    private List<String> countries = new ArrayList<>();
    private List<String> subjects = new ArrayList<>();
    private List<String> skills = new ArrayList<>();
}

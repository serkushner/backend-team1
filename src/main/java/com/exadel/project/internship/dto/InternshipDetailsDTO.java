package com.exadel.project.internship.dto;

import com.exadel.project.internship.entity.Format;
import com.exadel.project.skill.dto.SkillDTO;
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
    private InternshipTypeDTO internshipType;
    private List<CountryDTO> countries = new ArrayList<>();
    private List<SubjectDTO> subjects = new ArrayList<>();
    private List<SkillDTO> skills = new ArrayList<>();
}

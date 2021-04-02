package com.exadel.project.internship.dto;

import com.exadel.project.internship.entity.Format;
import com.exadel.project.internship.entity.InternshipType;
import com.exadel.project.internship.entity.Subject;
import com.exadel.project.skill.entity.Skill;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class InternshipInfoDTO {

    private String title;
    private List<SubjectDTO> subjects = new ArrayList<>();
    private List<SkillDTO> skills = new ArrayList<>();
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


}

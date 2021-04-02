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
    private List<String> countries = new ArrayList<>();
    private List<String> subjects = new ArrayList<>();
    private List<String> skills = new ArrayList<>();


}

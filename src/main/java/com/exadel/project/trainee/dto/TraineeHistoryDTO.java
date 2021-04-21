package com.exadel.project.trainee.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TraineeHistoryDTO {
    private Long internshipId;
    private String internshipTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> subjects;
    private String english;
    private String cv;
    private String github;
    private String techInterview;
    private String hrInterview;
}
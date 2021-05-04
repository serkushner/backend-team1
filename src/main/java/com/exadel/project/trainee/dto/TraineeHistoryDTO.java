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
    private Long additionalInfoId;
}
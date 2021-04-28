package com.exadel.project.interviewer.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InterviewerTimeDTO {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

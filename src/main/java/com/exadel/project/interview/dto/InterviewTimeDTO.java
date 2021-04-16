package com.exadel.project.interview.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InterviewTimeDTO {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

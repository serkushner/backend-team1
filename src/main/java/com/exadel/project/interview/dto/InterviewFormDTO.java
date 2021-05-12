package com.exadel.project.interview.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InterviewFormDTO {
    private Long id;
    private String subscription;
    private String english;
    private String traineeName;
    private String traineeSurname;
    private LocalDateTime interviewTime;
    private String interviewerName;
    private String interviewerSurname;
    private String interviewerDecision;
}

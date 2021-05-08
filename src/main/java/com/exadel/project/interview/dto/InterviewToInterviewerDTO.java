package com.exadel.project.interview.dto;

import lombok.Data;

@Data
public class InterviewToInterviewerDTO {
    private Long id;
    private String name;
    private InterviewTimeResponseDTO interviewTime;
    private Long traineeId;
    private Long internshipId;
}

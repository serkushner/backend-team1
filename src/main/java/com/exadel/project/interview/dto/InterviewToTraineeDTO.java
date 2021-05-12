package com.exadel.project.interview.dto;

import lombok.Data;

@Data
public class InterviewToTraineeDTO {
    private Long id;
    private String name;
    private InterviewTimeResponseDTO interviewTime;
    private Long internshipId;
    private Long interviewerId;
}

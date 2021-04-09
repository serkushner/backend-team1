package com.exadel.project.interview.dto;

import lombok.Data;

@Data
public class InterviewDTO {
    private Long id;
    private String name;
    private InterviewTimeDTO interviewTime;
}

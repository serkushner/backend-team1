package com.exadel.project.interview.dto;

import com.exadel.project.interviewer.dto.InterviewerDTO;
import com.exadel.project.trainee.dto.TraineeDTO;
import lombok.Data;

@Data
public class InterviewDTO {
    private Long id;
    private String name;
    private InterviewTimeDTO interviewTime;
    private TraineeDTO trainee;
    private InterviewerDTO interviewer;
}

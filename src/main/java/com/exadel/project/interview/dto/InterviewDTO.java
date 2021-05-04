package com.exadel.project.interview.dto;

import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.interviewer.dto.InterviewerResponseDTO;
import com.exadel.project.trainee.dto.TraineeDTO;
import lombok.Data;

@Data
public class InterviewDTO {
    private Long id;
    private String name;
    private InterviewTimeResponseDTO interviewTime;
    private TraineeDTO trainee;
    private InternshipDTO internshipDTO;
    private InterviewerResponseDTO interviewer;
}

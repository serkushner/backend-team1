package com.exadel.project.interview.dto;

import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.interviewer.dto.InterviewerDTO;
import com.exadel.project.trainee.dto.TraineeDTO;
import lombok.Data;

@Data
public class InterviewDTO {
    private Long id;
    private String name;
    private InterviewTimeDTO interviewTime;
    private TraineeDTO trainee;
    private InternshipDTO internshipDTO;
    private InterviewerDTO interviewer;
}

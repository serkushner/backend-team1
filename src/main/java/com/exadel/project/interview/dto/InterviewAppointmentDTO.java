package com.exadel.project.interview.dto;

import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.interviewer.dto.InterviewerDTO;
import com.exadel.project.trainee.dto.TraineeDTO;
import lombok.Data;

@Data
public class InterviewAppointmentDTO {
    private Long id;
    private InterviewTimeDTO interviewTime;
    private Long traineeId;
    private Long internshipId;
    private Long interviewerId;
}

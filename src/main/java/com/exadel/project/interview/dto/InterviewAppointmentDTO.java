package com.exadel.project.interview.dto;

import lombok.Data;

@Data
public class InterviewAppointmentDTO {
    private Long interviewTimeId;
    private Long traineeId;
    private Long internshipId;
    private Long interviewerId;
}

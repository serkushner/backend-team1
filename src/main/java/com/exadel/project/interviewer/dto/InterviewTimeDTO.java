package com.exadel.project.interviewer.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InterviewTimeDTO {
    private Long interviewTimeId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<InterviewerAppointmentDTO> interviewers;
}

package com.exadel.project.interview.dto;

import com.exadel.project.interviewer.dto.InterviewerAppointmentDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InterviewTimeAppointmentDTO {
    private Long interviewTimeId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<InterviewerAppointmentDTO> interviewers;
}

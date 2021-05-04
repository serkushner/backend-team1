package com.exadel.project.interviewer.dto;

import com.exadel.project.interview.dto.InterviewTimeDTO;
import lombok.Data;

import java.util.List;

@Data
public class InterviewerAppointmentDTO {
    private Long id;
    private String name;
    private String surname;
    private List<InterviewTimeDTO> interviewerTimes;
}

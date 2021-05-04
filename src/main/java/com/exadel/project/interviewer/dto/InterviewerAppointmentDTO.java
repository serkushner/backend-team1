package com.exadel.project.interviewer.dto;

import com.exadel.project.interview.entity.InterviewTime;
import lombok.Data;

import java.util.List;

@Data
public class InterviewerAppointmentDTO {
    private Long interviewerId;
    private String name;
    private String surname;
}

package com.exadel.project.interviewer.dto;

import com.exadel.project.interview.entity.InterviewTime;
import lombok.Data;

import java.util.List;

@Data
public class InterviewerAppointmentDTO {
    private Long id;
    private String name;
    private String surname;
    private List<InterviewTime> interviewerTimes;
}

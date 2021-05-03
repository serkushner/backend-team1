package com.exadel.project.interviewer.dto;

import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.dto.InterviewTimeRequestDTO;
import com.exadel.project.interviewer.entity.InterviewerType;
import lombok.Data;

import java.util.List;

@Data
public class InterviewerRequestDTO {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private InterviewerType type;
    private String skype;
    private List<InterviewTimeRequestDTO> interviewTimes;
    private List<String> subjects;
}

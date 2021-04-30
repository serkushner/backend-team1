package com.exadel.project.interviewer.dto;

import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.dto.InterviewTimeDTO;
import com.exadel.project.interviewer.entity.InterviewerType;
import com.exadel.project.subject.dto.SubjectDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InterviewerDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private InterviewerType type;
    private String skype;
    private List<InterviewDTO> interviews;
    private List<InterviewTimeDTO> interviewTimes;
    private List<String> subjects;
}

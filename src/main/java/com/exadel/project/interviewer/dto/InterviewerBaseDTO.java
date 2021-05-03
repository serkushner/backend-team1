package com.exadel.project.interviewer.dto;

import com.exadel.project.interviewer.entity.InterviewerType;
import lombok.Data;

import java.util.List;

@Data
public class InterviewerBaseDTO {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private InterviewerType type;
    private String skype;
    private List<String> subjects;
}

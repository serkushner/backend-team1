package com.exadel.project.internship.dto;

import com.exadel.project.internship.entity.InterviewerType;
import lombok.Data;

@Data
public class InterviewerDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private InterviewerType type;
    private String skype;

}

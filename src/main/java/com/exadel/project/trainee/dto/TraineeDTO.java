package com.exadel.project.trainee.dto;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.internship.entity.Country;
import com.exadel.project.trainee.entity.InterviewPeriod;
import com.exadel.project.trainee.entity.TraineeStatus;

import java.util.ArrayList;
import java.util.List;

public class TraineeDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Country country;
    private TraineeStatus status;   //default - in Mapper
    private String linkedInLink;
    private String skype;
    private AdministratorDto administrator;
    private Boolean recipient;
    private List<InterviewPeriod> interviewPeriods = new ArrayList<>();
}

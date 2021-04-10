package com.exadel.project.trainee.dto;


import java.util.ArrayList;
import java.util.List;

public class TraineeDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String country;
    private String traineeStatus;
    private String linkedInLink;
    private String skype;
    private AdministratorDTO administrator;
    private Boolean recipient;
    private List<InterviewPeriodDTO> interviewPeriods =
            new ArrayList<>();
}

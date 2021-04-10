package com.exadel.project.trainee.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TraineeDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String location;
    private String traineeStatus;
    private String skype;
    private Boolean recipient;
    private String english;
    private String cv;
    private String github;
    private String day1;
    private String day2;
    private String day3;
    private String hours1;
    private String hours2;
    private String hours3;
}

package com.exadel.project.trainee.dto;

import lombok.Data;

import java.util.Map;

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
    private Map<String, Map<String, String>> dates;
}

package com.exadel.project.trainee.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TraineeToAdminDetailsDTO {

    private Long traineeId;
    private String name;
    private String surname;
    private String phone;
    private String github;
    private String skype;
    private String cv;
    private String location;
    private String english;
    private String adminName;
    private String adminSurname;
    private List<Map<String, String>> dates;
    private String internshipTitle;
    private String techInterview;
    private String hrInterview;
}

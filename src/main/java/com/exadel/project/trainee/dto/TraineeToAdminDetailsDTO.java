package com.exadel.project.trainee.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class TraineeToAdminDetailsDTO {
    private Long id;
    private Long traineeId;
    private String name;
    private String surname;
    private String phone;
    private String location;
    private String github;
    private String skype;
    private String cv;
    private String english;
    private Boolean recipient;
    private String email;
    private String traineeStatus;
    private List<Map<String, String>> dates;
    private Long InternshipId;
    private String internshipTitle;
    private String techInterview;
    private String hrInterview;
    private List<String> subjects;
    private LocalDateTime hrInterviewTime;
    private String hrName;
    private String hrSurname;
    private LocalDateTime techInterviewTime;
    private String techName;
    private String techSurname;
}
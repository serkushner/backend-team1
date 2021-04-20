package com.exadel.project.trainee.dto;

import lombok.Data;
import java.util.List;

@Data
public class TraineeToAdminDTO {

    private Long id;
    private String traineeName;
    private String traineeSurname;
    private String email;
    private List<String> subjects;
    private String traineeStatus;
    private String adminName;
    private String adminSurname;
    private String internshipName;
    private String traineeLocation;
}
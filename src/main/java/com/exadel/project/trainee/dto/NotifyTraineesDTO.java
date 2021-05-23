package com.exadel.project.trainee.dto;

import lombok.Data;

import java.util.List;

@Data
public class NotifyTraineesDTO {
    private String message;
    private List<Long> additionalInfoIds;
}
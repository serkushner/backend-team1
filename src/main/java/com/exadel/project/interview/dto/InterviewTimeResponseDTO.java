package com.exadel.project.interview.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class InterviewTimeResponseDTO extends InterviewTimeBaseDTO{
    private Long id;
    private LocalDateTime endDate;
}
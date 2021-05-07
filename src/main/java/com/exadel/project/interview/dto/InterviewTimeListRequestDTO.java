package com.exadel.project.interview.dto;

import lombok.Data;

import java.util.List;

@Data
public class InterviewTimeListRequestDTO {
    private List<InterviewTimeRequestDTO> interviewTimes;
}

package com.exadel.project.interviewer.dto;

import com.exadel.project.interview.dto.InterviewTimeRequestDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class InterviewerRequestDTO extends InterviewerBaseDTO {
    private List<InterviewTimeRequestDTO> interviewTimes;
}

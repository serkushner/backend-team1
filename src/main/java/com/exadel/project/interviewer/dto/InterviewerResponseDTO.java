package com.exadel.project.interviewer.dto;

import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.dto.InterviewTimeResponseDTO;
import com.exadel.project.interview.dto.InterviewToInterviewerDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class InterviewerResponseDTO extends InterviewerBaseDTO{
    private Long id;
    private List<InterviewToInterviewerDTO> interviews;
    private List<InterviewTimeResponseDTO> interviewTimes;
}

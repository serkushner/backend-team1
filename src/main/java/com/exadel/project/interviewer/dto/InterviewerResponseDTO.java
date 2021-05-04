package com.exadel.project.interviewer.dto;

import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.dto.InterviewTimeResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class InterviewerResponseDTO extends InterviewerBaseDTO{
    private Long id;
    private List<InterviewDTO> interviews;
    private List<InterviewTimeResponseDTO> interviewTimes;
}

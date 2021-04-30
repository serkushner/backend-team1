package com.exadel.project.interview.service;

import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.interview.dto.InterviewTimeDTO;
import com.exadel.project.interview.entity.InterviewTime;
import com.exadel.project.interview.mapper.InterviewTimeMapper;
import com.exadel.project.interview.repository.InterviewTimeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InterviewTimeService extends BaseService<InterviewTime, InterviewTimeRepository> {
    private static final int INTERVIEW_DURATION_MINUTES = 60;

    private final InterviewTimeRepository interviewTimeRepository;
    private final InterviewTimeMapper interviewTimeMapper;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public InterviewTimeDTO saveInterviewTime(InterviewTimeDTO interviewTimeDTO){
        interviewTimeDTO.setEndDate(interviewTimeDTO.getStartDate().plusMinutes(INTERVIEW_DURATION_MINUTES));
        InterviewTime interviewTime = interviewTimeRepository.findByStartDateAndEndDate(interviewTimeDTO.getStartDate(), interviewTimeDTO.getEndDate());
        if (interviewTime == null){
            interviewTime = interviewTimeMapper.dtoToEntity(interviewTimeDTO);
            interviewTime = interviewTimeRepository.save(interviewTime);
        }
        return interviewTimeMapper.entityToDto(interviewTime);
    }
}

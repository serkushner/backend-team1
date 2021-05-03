package com.exadel.project.interview.service;

import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.interview.dto.InterviewTimeRequestDTO;
import com.exadel.project.interview.dto.InterviewTimeResponseDTO;
import com.exadel.project.interview.entity.InterviewTime;
import com.exadel.project.interview.mapper.InterviewTimeMapper;
import com.exadel.project.interview.repository.InterviewTimeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class InterviewTimeService extends BaseService<InterviewTime, InterviewTimeRepository> {

    private final InterviewTimeRepository interviewTimeRepository;
    private final InterviewTimeMapper interviewTimeMapper;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public InterviewTimeResponseDTO saveInterviewTime(InterviewTimeRequestDTO interviewTimeRequestDTO, long interviewDurationMinutes){
        InterviewTime interviewTime = interviewTimeRepository.findByStartDateAndEndDate(interviewTimeRequestDTO.getStartDate(), interviewTimeRequestDTO.getStartDate().plusMinutes(interviewDurationMinutes))
                .orElseGet(()->{
                    InterviewTime time = interviewTimeMapper.dtoToEntity(interviewTimeRequestDTO);
                    time.setEndDate(time.getStartDate().plusMinutes(interviewDurationMinutes));
                    return interviewTimeRepository.save(time);
                });
        return interviewTimeMapper.entityToDto(interviewTime);
    }

    public List<InterviewTime> getInterviewTimesByIds(List<Long> ids){
        return interviewTimeRepository.findAllByIdIn(ids);
    }
}

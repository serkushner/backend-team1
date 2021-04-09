package com.exadel.project.interview.service;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.common.exception.EntityAlreadyExistsException;
import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.interview.mapper.InterviewMapper;
import com.exadel.project.interview.repository.InterviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InterviewService extends BaseService<Interview, InterviewRepository> {

    private final InterviewMapper interviewMapper;

    private final InterviewRepository interviewRepository;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public InterviewDTO createInterview(InterviewDTO interviewDTO) throws EntityAlreadyExistsException {
        if (interviewRepository.findById(interviewDTO.getId()).isPresent()) {
            throw new EntityAlreadyExistsException();
        }
        Interview interview = interviewMapper.dtoToEntity(interviewDTO);
        interviewRepository.save(interview);
        return interviewMapper.entityToDto(interview);
    }

    public void deleteInterviewById(Long id) throws EntityNotFoundException {
        interviewRepository.delete(interviewRepository.findInterviewById(id));
    }

    private Interview getInterviewById(Long id) throws EntityNotFoundException {
        return Optional.ofNullable(interviewRepository.findInterviewById(id))
                .orElseThrow(EntityNotFoundException::new);
    }


}

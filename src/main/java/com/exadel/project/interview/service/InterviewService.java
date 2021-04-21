package com.exadel.project.interview.service;

import com.exadel.project.common.exception.EntityAlreadyExistsException;
import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.interview.mapper.InterviewMapper;
import com.exadel.project.interview.repository.InterviewRepository;
import com.exadel.project.trainee.entity.Trainee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterviewService extends BaseService<Interview, InterviewRepository> {

    private final InterviewMapper interviewMapper;

    private final InterviewRepository interviewRepository;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public InterviewDTO addInterview(InterviewDTO interviewDTO) throws EntityAlreadyExistsException {
        if (interviewRepository.findById(interviewDTO.getId()).isPresent()) {
            throw new EntityAlreadyExistsException();
        }
        Interview interview = interviewMapper.dtoToEntity(interviewDTO);
        interviewRepository.save(interview);
        return interviewMapper.entityToDto(interview);
    }

    public Interview getInterviewById(Long id) throws EntityNotFoundException {
        return interviewRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public InterviewDTO updateInterviewById(Long id, InterviewDTO interviewDTO) throws EntityNotFoundException {
        Interview interview = interviewMapper.dtoToEntity(interviewDTO);
        interview.setId(getEntityById(id).getId());
        Interview newInterview = interviewRepository.save(interview);
        return interviewMapper.entityToDto(newInterview);
    }

    public void deleteInterviewById(Long id) throws EntityNotFoundException {
        interviewRepository.delete(interviewRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<InterviewDTO> getAll() {
        return interviewRepository.findAll()
                .stream()
                .map(interviewMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public List<InterviewDTO> getAllByInterviewerId(Long id) {
        return interviewRepository.findAllByInterviewerId(id)
                .stream()
                .map(interviewMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public List<InterviewDTO> getAllByTraineeAndInternshipId(Trainee trainee, Internship internship) {
        return interviewRepository.findAllByTraineeAndInternship(trainee, internship)
                .stream()
                .map(interviewMapper::entityToDto)
                .collect(Collectors.toList());
    }
}

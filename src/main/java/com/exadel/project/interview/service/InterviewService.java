package com.exadel.project.interview.service;

import com.exadel.project.common.exception.EntityAlreadyExistsException;
import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.service.InternshipService;
import com.exadel.project.interview.dto.InterviewAppointmentDTO;
import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.interview.mapper.InterviewAppointmentMapper;
import com.exadel.project.interview.mapper.InterviewMapper;
import com.exadel.project.interview.repository.InterviewRepository;
import com.exadel.project.interviewer.service.InterviewerService;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InterviewService extends BaseService<Interview, InterviewRepository> {

    private final InterviewMapper interviewMapper;
    private final  InterviewRepository interviewRepository;
    @Lazy
    @Autowired
    private  TraineeService traineeService;
    private final  InternshipService internshipService;
    private final  InterviewerService interviewerService;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public InterviewAppointmentDTO addInterview(InterviewAppointmentDTO interviewAppointmentDTO) throws EntityAlreadyExistsException {
        Interview interview = new Interview();
        interview.setInternship(internshipService.getEntityById(interviewAppointmentDTO.getInternshipId()));
        interview.setTrainee(traineeService.getEntityById(interviewAppointmentDTO.getTraineeId()));
        interview.setInterviewer(interviewerService.getEntityById(interviewAppointmentDTO.getInterviewerId()));
        interviewRepository.save(interview);
        //TODO send emails
        return interviewAppointmentDTO;
    }

    public InterviewDTO getInterviewById(Long id) throws EntityNotFoundException {
        return interviewMapper.entityToDto(interviewRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
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

    public List<InterviewDTO> getAllByTraineeAndInternshipId(Long traineeId, Long internshipId) {
        return interviewRepository.findAllByTraineeIdAndInternshipId(traineeId, internshipId)
                .stream()
                .map(interviewMapper::entityToDto)
                .collect(Collectors.toList());
    }
}

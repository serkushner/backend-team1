package com.exadel.project.interview.service;

import com.exadel.project.common.exception.EntityAlreadyExistsException;
import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.internship.service.InternshipService;
import com.exadel.project.interview.dto.InterviewAppointmentDTO;
import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.dto.InterviewFormDTO;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.interview.event.InterviewCreatedEvent;
import com.exadel.project.interview.mapper.InterviewFormMapper;
import com.exadel.project.interview.mapper.InterviewMapper;
import com.exadel.project.interview.repository.InterviewRepository;
import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.interviewer.entity.InterviewerType;
import com.exadel.project.interviewer.service.InterviewerService;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.EnglishLevel;
import com.exadel.project.trainee.entity.TraineeStatus;
import com.exadel.project.trainee.repository.AdditionalInfoRepository;
import com.exadel.project.trainee.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InterviewService extends BaseService<Interview, InterviewRepository> {

    private final InterviewMapper interviewMapper;
    private final InterviewRepository interviewRepository;
    @Lazy
    @Autowired
    private TraineeService traineeService;
    private final InternshipService internshipService;
    private final InterviewerService interviewerService;
    private final InterviewTimeService interviewTimeService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final InterviewFormMapper interviewFormMapper;
    private final AdditionalInfoRepository additionalInfoRepository;
    private final JwtInterviewService jwtInterviewService;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    @Transactional
    public InterviewAppointmentDTO addInterview(InterviewAppointmentDTO interviewAppointmentDTO) throws EntityAlreadyExistsException {
        List<Interview> interviews = interviewRepository.findAllByTraineeIdAndInternshipId(interviewAppointmentDTO.getTraineeId(), interviewAppointmentDTO.getInternshipId());
        Interviewer interviewer = interviewerService.getEntityById(interviewAppointmentDTO.getInterviewerId());
        InterviewerType interviewerType = interviewer.getType();
        Optional<Interview> interviewOptional = interviews.stream().filter(interview -> interview.getInterviewer().getType() == interviewerType).findAny();
        Interview interview;
        if (interviewOptional.isPresent()){
            interview = interviewOptional.get();
            Interviewer oldInterviewer = interview.getInterviewer();
            oldInterviewer.getInterviewTimes().add(interview.getInterviewTime());
        }else {
            interview = new Interview();
            interview.setInternship(internshipService.getEntityById(interviewAppointmentDTO.getInternshipId()));
            interview.setTrainee(traineeService.getEntityById(interviewAppointmentDTO.getTraineeId()));
        }

        interview.setInterviewTime(interviewTimeService.getEntityById(interviewAppointmentDTO.getInterviewTimeId()));
        interviewerService.deleteInterview(interviewer, interviewTimeService.getEntityById(interviewAppointmentDTO.getInterviewTimeId()));
        interview.setInterviewer(interviewer);
        interviewRepository.save(interview);
        changeTraineeStatusAfterAddInterview(interview);
        InterviewCreatedEvent interviewCreatedEvent = new InterviewCreatedEvent(this, interview);
        applicationEventPublisher.publishEvent(interviewCreatedEvent);
        return interviewAppointmentDTO;
    }

    @Transactional
    public void changeTraineeStatusAfterAddInterview(Interview interview){
        AdditionalInfo additionalInfo = additionalInfoRepository.findAdditionalInfoByInternshipAndTrainee(interview.getInternship(), interview.getTrainee());
        if (additionalInfo.getTraineeStatus() == TraineeStatus.REGISTERED || additionalInfo.getTraineeStatus() == TraineeStatus.RECRUITER_INTERVIEW_PASSED){
            TraineeStatus traineeStatus = TraineeStatus.getNextStatus(additionalInfo.getTraineeStatus(), true);
            additionalInfo.setTraineeStatus(traineeStatus);
            additionalInfoRepository.save(additionalInfo);
        }
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

    @Transactional
    public InterviewFormDTO getInterviewFormInfoByToken(String encryptedInterviewId){
        jwtInterviewService.validateToken(encryptedInterviewId);
        Long interviewId = Long.valueOf(jwtInterviewService.getIdFromToken(encryptedInterviewId));
        Interview interview = interviewRepository.findById(interviewId).orElseThrow(EntityNotFoundException::new);
        AdditionalInfo additionalInfo = additionalInfoRepository.findAdditionalInfoByInternshipAndTrainee(interview.getInternship(), interview.getTrainee());
        return interviewFormMapper.entityToDto(interview, additionalInfo);
    }

    @Transactional
    public InterviewFormDTO updateInterviewForm(InterviewFormDTO dto){
        Interview interview = interviewRepository.findById(dto.getId())
                .orElseThrow(EntityNotFoundException::new);
        AdditionalInfo additionalInfo = additionalInfoRepository.findAdditionalInfoByInternshipAndTrainee(interview.getInternship(), interview.getTrainee());
        additionalInfo.setEnglish(EnglishLevel.valueOf(dto.getEnglish().toUpperCase()));
        TraineeStatus status = TraineeStatus.getNextStatus(additionalInfo.getTraineeStatus(), dto.getInterviewerDecision());
        additionalInfo.setTraineeStatus(status);
        additionalInfoRepository.save(additionalInfo);
        interview.setName(dto.getSubscription());
        interviewRepository.save(interview);
        return interviewFormMapper.entityToDto(interview, additionalInfo);
    }
}
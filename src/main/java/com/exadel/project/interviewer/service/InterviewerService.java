package com.exadel.project.interviewer.service;

import com.exadel.project.common.exception.EntityAlreadyExistsException;
import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.interview.dto.InterviewTimeRequestDTO;
import com.exadel.project.interview.dto.InterviewTimeResponseDTO;
import com.exadel.project.interviewer.dto.InterviewerRequestDTO;
import com.exadel.project.interviewer.dto.InterviewerResponseDTO;
import com.exadel.project.interviewer.entity.InterviewerType;
import com.exadel.project.subject.entity.Subject;
import com.exadel.project.interview.entity.InterviewTime;
import com.exadel.project.interview.mapper.InterviewTimeMapper;
import com.exadel.project.interview.service.InterviewTimeService;
import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.interviewer.mapper.InterviewerMapper;
import com.exadel.project.interviewer.repository.InterviewerRepository;
import com.exadel.project.subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class InterviewerService extends BaseService<Interviewer, InterviewerRepository> {
    {
        defaultSortingField = "type";
        defaultSortingDirection = "desc";
    }
    @Value("${interview.tech.duration}")
    private Long techInterviewDuration;
    @Value("${interview.hr.duration}")
    private Long hrInterviewDuration;

    private final InterviewerMapper interviewerMapper;
    private final InterviewerRepository interviewerRepository;
    private final InterviewTimeService interviewTimeService;
    private final InterviewTimeMapper interviewTimeMapper;
    private final SubjectService subjectService;

    public List<InterviewerResponseDTO> getAll(String search, String sortFields) {
        Sort sort = getSort(sortFields);
        return super.findBySpecifications(search, sort)
                .stream()
                .map(interviewerMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public InterviewerResponseDTO getById(Long id) throws EntityNotFoundException {
        return interviewerMapper.entityToDto(super.getEntityById(id));
    }

    @Transactional
    public InterviewerResponseDTO addInterviewer(InterviewerRequestDTO interviewerRequestDTO) {
        checkDoubleRegistration(interviewerRequestDTO.getEmail());
        Interviewer interviewer = interviewerMapper.dtoToEntity(interviewerRequestDTO);
        interviewer.setSubjects(getSubjectsByNames(interviewerRequestDTO.getSubjects()));
        interviewer = interviewerRepository.save(interviewer);
        addInterviewTimeToInterviewer(interviewerRequestDTO.getInterviewTimes(), interviewer.getId());
        return interviewerMapper.entityToDto(interviewer);
    }

    public InterviewerResponseDTO updateInterviewer(Long interviewerId, InterviewerRequestDTO interviewerRequestDTO) {
        Interviewer interviewer = interviewerMapper.dtoToEntity(interviewerRequestDTO);
        interviewer.setSubjects(getSubjectsByNames(interviewerRequestDTO.getSubjects()));
        interviewer.setId(getEntityById(interviewerId).getId());
        Interviewer savedInterviewer = interviewerRepository.save(interviewer);
        addInterviewTimeToInterviewer(interviewerRequestDTO.getInterviewTimes(), savedInterviewer.getId());
        return interviewerMapper.entityToDto(savedInterviewer);
    }

    public void deleteInterviewer(Long id) throws EntityNotFoundException {
        interviewerRepository.delete(getEntityById(id));
    }

    @Transactional
    public List<InterviewTimeResponseDTO> addInterviewTimeToInterviewer(List<InterviewTimeRequestDTO> interviewTimeRequestDTOS, Long interviewerId){
        Interviewer interviewer = getEntityById(interviewerId);
        Long duration = interviewer.getType() == InterviewerType.TECH ? techInterviewDuration : hrInterviewDuration;
        List<InterviewTime> interviewTimeList = interviewTimeRequestDTOS.stream()
                .map(interviewTimeRequestDTO -> interviewTimeService.saveInterviewTime(interviewTimeRequestDTO, duration))
                .map(interviewTimeMapper::dtoToEntity)
                .collect(Collectors.toList());
        interviewer.setInterviewTimes(interviewTimeList);
        return interviewTimeList.stream().map(interviewTimeMapper::entityToDto).collect(Collectors.toList());
    }

    private List<Subject> getSubjectsByNames(List<String> subjectNames){
        return subjectNames.stream().map(subjectService::getByName).collect(Collectors.toList());
    }

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public void checkDoubleRegistration(String email){
        if (interviewerRepository.findByEmail(email).isPresent()){
            throw new EntityAlreadyExistsException();
        }
    }
}
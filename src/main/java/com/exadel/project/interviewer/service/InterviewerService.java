package com.exadel.project.interviewer.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.internship.entity.Subject;
import com.exadel.project.interviewer.dto.InterviewerAppointmentDTO;
import com.exadel.project.interviewer.dto.InterviewerDTO;
import com.exadel.project.interviewer.dto.SubjectDTO;
import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.interviewer.entity.InterviewerType;
import com.exadel.project.interviewer.mapper.InterviewerAppointmentMapper;
import com.exadel.project.interviewer.mapper.InterviewerMapper;
import com.exadel.project.interviewer.mapper.SubjectMapper;
import com.exadel.project.interviewer.repository.InterviewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterviewerService extends BaseService<Interviewer, InterviewerRepository> {

    private final InterviewerMapper interviewerMapper;
    private final InterviewerRepository interviewerRepository;
    private final SubjectMapper subjectMapper;
    private final InterviewerAppointmentMapper interviewerAppointmentMapper;

    {
        defaultSortingField = "type";
        defaultSortingDirection = "desc";
    }

    public List<InterviewerDTO> getAll(String search, String sortFields) {
        Sort sort = getSort(sortFields);
        return super.findBySpecifications(search, sort)
                .stream()
                .map(interviewerMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public List<InterviewerAppointmentDTO> getAllAvailable(InterviewerType interviewerType, List<SubjectDTO> subjectDTOS) {
        //TODO sorting by dates
        if (interviewerType == InterviewerType.TECH) {
            List<Subject> subjects = subjectDTOS.stream()
                    .map(subjectMapper::dtoToEntity)
                    .collect(Collectors.toList());
            List<Interviewer> interviewers = interviewerRepository.findAllBySubjectsIn(subjects);
            return interviewers.stream()
                    .map(interviewerAppointmentMapper::entityToDto)
                    .collect(Collectors.toList());
        } else {
            return interviewerRepository.findAll().stream()
                    .map(interviewerAppointmentMapper::entityToDto)
                    .collect(Collectors.toList());
        }
    }

    public InterviewerDTO getById(Long id) throws EntityNotFoundException {
        return interviewerMapper.entityToDto(super.getEntityById(id));
    }

    public InterviewerDTO addInterviewer(InterviewerDTO interviewerDto) {
        Interviewer interviewer = interviewerMapper.dtoToEntity(interviewerDto);
        Interviewer savedInterviewer = interviewerRepository.save(interviewer);
        return interviewerMapper.entityToDto(savedInterviewer);
    }

    public InterviewerDTO updateInterviewer(Long id, InterviewerDTO interviewerDto) {
        Interviewer interviewer = interviewerMapper.dtoToEntity(interviewerDto);
        interviewer.setId(getEntityById(id).getId());
        Interviewer savedInterviewer = interviewerRepository.save(interviewer);
        return interviewerMapper.entityToDto(savedInterviewer);
    }

    public void deleteInterviewer(Long id) throws EntityNotFoundException {
        interviewerRepository.delete(getEntityById(id));
    }

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }
}

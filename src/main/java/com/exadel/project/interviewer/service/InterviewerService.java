package com.exadel.project.interviewer.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.internship.entity.Subject;
import com.exadel.project.internship.service.SubjectService;
import com.exadel.project.interview.dto.InterviewTimeDTO;
import com.exadel.project.interview.entity.InterviewTime;
import com.exadel.project.interview.mapper.InterviewTimeMapper;
import com.exadel.project.interview.service.InterviewTimeService;
import com.exadel.project.interviewer.dto.InterviewerDTO;
import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.interviewer.mapper.InterviewerMapper;
import com.exadel.project.interviewer.repository.InterviewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterviewerService extends BaseService<Interviewer, InterviewerRepository> {
    {
        defaultSortingField = "type";
        defaultSortingDirection = "desc";
    }
    private final InterviewerMapper interviewerMapper;
    private final InterviewerRepository interviewerRepository;
    private final InterviewTimeService interviewTimeService;
    private final InterviewTimeMapper interviewTimeMapper;
    private final SubjectService subjectService;

    public List<InterviewerDTO> getAll(String search, String sortFields) {
        Sort sort = getSort(sortFields);
        return super.findBySpecifications(search, sort)
                .stream()
                .map(interviewerMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public InterviewerDTO getById(Long id) throws EntityNotFoundException {
        return interviewerMapper.entityToDto(super.getEntityById(id));
    }

    public InterviewerDTO addInterviewer(InterviewerDTO interviewerDto) {
        Interviewer interviewer = interviewerMapper.dtoToEntity(interviewerDto);
        interviewer.setSubjects(getSubjectsByNames(interviewerDto.getSubjects()));
        Interviewer savedInterviewer = interviewerRepository.save(interviewer);
        return interviewerMapper.entityToDto(savedInterviewer);
    }

    public InterviewerDTO updateInterviewer(Long id, InterviewerDTO interviewerDto) {
        Interviewer interviewer = interviewerMapper.dtoToEntity(interviewerDto);
        interviewer.setSubjects(getSubjectsByNames(interviewerDto.getSubjects()));
        interviewer.setId(getEntityById(id).getId());
        Interviewer savedInterviewer = interviewerRepository.save(interviewer);
        return interviewerMapper.entityToDto(savedInterviewer);
    }

    public void deleteInterviewer(Long id) throws EntityNotFoundException {
        interviewerRepository.delete(getEntityById(id));
    }

    @Transactional
    public InterviewTimeDTO addInterviewTimeToInterviewer(InterviewTimeDTO interviewTimeDTO, Long id){
        interviewTimeDTO = interviewTimeService.saveInterviewTime(interviewTimeDTO);
        InterviewTime interviewTime = interviewTimeMapper.dtoToEntity(interviewTimeDTO);
        Interviewer interviewer = getEntityById(id);
        interviewer.getInterviewTimes().add(interviewTime);
        return interviewTimeDTO;
    }

    @Transactional
    public void deleteInterviewTimeFromInterviewer(InterviewTimeDTO interviewTimeDTO, Long id){
        InterviewTime interviewTime = interviewTimeMapper.dtoToEntity(interviewTimeDTO);
        Interviewer interviewer = getEntityById(id);
        interviewer.getInterviewTimes().remove(interviewTime);
        interviewTimeService.deleteInterviewTime(interviewTimeDTO);
    }

    private List<Subject> getSubjectsByNames(List<String> subjectNames){
        return subjectNames.stream().map(subjectService::findSubjectByName).collect(Collectors.toList());
    }

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }
}

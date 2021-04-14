package com.exadel.project.interviewer.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.interviewer.dto.InterviewerDto;
import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.interviewer.mapper.InterviewerMapper;
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

    {
        defaultSortingField = "type";
        defaultSortingDirection = "desc";
    }

    public List<InterviewerDto> getAll(String search, String sortFields) {
        Sort sort = getSort(sortFields);
        return super.findBySpecifications(search, sort)
                .stream()
                .map(interviewerMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public InterviewerDto getById(Long id) throws EntityNotFoundException {
        return interviewerMapper.entityToDto(super.getEntityById(id));
    }

    public InterviewerDto createInterviewer(InterviewerDto interviewerDto) {
        Interviewer interviewer = interviewerMapper.dtoToEntity(interviewerDto);
        Interviewer savedInterviewer = interviewerRepository.save(interviewer);
        return interviewerMapper.entityToDto(savedInterviewer);
    }

    public InterviewerDto updateInterviewer(Long id, InterviewerDto interviewerDto) {
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

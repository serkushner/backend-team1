package com.exadel.project.internship.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.internship.dto.InterviewerDto;
import com.exadel.project.internship.entity.Interviewer;
import com.exadel.project.internship.mapper.InterviewerMapper;
import com.exadel.project.internship.repository.InterviewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterviewerService extends BaseService<Interviewer, InterviewerRepository> {

    private final InterviewerMapper interviewerMapper;

    private final InterviewerRepository interviewerRepository;

    public List<InterviewerDto> getAll(String search) {
        return super.getAllEntities(search)
                .stream()
                .sorted(Comparator.comparing(Interviewer::getType))
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
        Interviewer interviewer = getEntityById(id);
        fillInterviewer(interviewerDto, interviewer);
        Interviewer savedInterviewer = interviewerRepository.save(interviewer);
        return interviewerMapper.entityToDto(savedInterviewer);
    }

    public void deleteInterviewer(Long id) throws EntityNotFoundException{
        interviewerRepository.delete(getEntityById(id));
    }

    private void fillInterviewer(InterviewerDto interviewerDto, Interviewer interviewer) {
        interviewer.setName(interviewerDto.getName());
        interviewer.setSurname(interviewerDto.getSurname());
        interviewer.setEmail(interviewerDto.getEmail());
        interviewer.setPhone(interviewerDto.getPhone());
        interviewer.setType(interviewerDto.getType());
        interviewer.setSkype(interviewerDto.getSkype());
    }


}

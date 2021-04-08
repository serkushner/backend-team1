package com.exadel.project.service;

import com.exadel.project.internship.dto.InterviewerDto;
import com.exadel.project.internship.entity.Interviewer;
import com.exadel.project.internship.entity.InterviewerType;
import com.exadel.project.internship.mapper.InterviewerMapperImpl;
import com.exadel.project.internship.repository.InterviewerRepository;
import com.exadel.project.internship.service.InterviewerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class InterviewerServiceTest {

    @Autowired
    private InterviewerService interviewerService;

    @Autowired
    private InterviewerMapperImpl interviewerMapper;

    @MockBean
    private InterviewerRepository interviewerRepository;

    @Test
    void testGetInterviewerById() {
        Interviewer interviewer = new Interviewer();
        interviewer.setId(1L);
        interviewer.setName("Alex");
        interviewer.setSurname("Grishin");
        interviewer.setEmail("a.grishin@yandex.by");
        interviewer.setType(InterviewerType.TECH);

        InterviewerDto dto = interviewerMapper.entityToDto(interviewer);

        doReturn(Optional.of(interviewer)).when(interviewerRepository).findById(1L);

        InterviewerDto returnedInterviewerDto = interviewerService.getById(1L);

        Assertions.assertNotNull(returnedInterviewerDto, "Returned object is not null");
        Assertions.assertEquals(dto, returnedInterviewerDto);

    }

    @Test
    void testFindAllInterviewers() {
        Interviewer firstInterviewer = new Interviewer();
        firstInterviewer.setName("Kate");
        firstInterviewer.setSurname("Surganova");
        firstInterviewer.setType(InterviewerType.HR);
        Interviewer secondInterviewer = new Interviewer();
        secondInterviewer.setName("Sergei");
        secondInterviewer.setSurname("Skripnikov");
        secondInterviewer.setType(InterviewerType.TECH);

        doReturn(Arrays.asList(firstInterviewer,secondInterviewer)).when(interviewerRepository).findAll((Sort) null);

        List<Interviewer> interviewerList = interviewerService.findBySpecifications(null,null);

        //todo find a way to return Dto

        Assertions.assertEquals(2,interviewerList.size(),"Method getAll should return 2 interviewers");

    }

    @Test
    void testSaveInterviewer(){
        Interviewer interviewer = new Interviewer();
        interviewer.setId(1L);
        interviewer.setName("Dmitry");
        interviewer.setSurname("Vladimirov");
        interviewer.setType(InterviewerType.TECH);

        InterviewerDto dto = interviewerMapper.entityToDto(interviewer);

        doReturn(interviewer).when(interviewerRepository).save(interviewer);

        InterviewerDto savedDto = interviewerService.createInterviewer(dto);

        Assertions.assertNotNull(savedDto,"Saved Interviewer should not be empty");
        Assertions.assertEquals(dto,savedDto);
    }



}

package com.exadel.project.interview.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.interview.entity.InterviewTestData;
import com.exadel.project.interview.mapper.InterviewMapper;
import com.exadel.project.interview.repository.InterviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
@DisplayName("CRUD tests with Interview's Entity")
public class InterviewCrudTest {

    @MockBean
    InterviewRepository interviewRepository;

    @Autowired
    InterviewTestData interviewTestData;

    @Autowired
    InterviewService interviewService;

    @Autowired
    InterviewMapper interviewMapper;

    @Nested
    @DisplayName("Check Interview's getById method")
    class testGetByIdMethod {

        @Test
        @DisplayName("When Interview exists Then return it from database")
        public void getInterviewByIdTest() {
            Interview interview = interviewTestData.getTechInterview();

            doReturn(Optional.ofNullable(interview)).when(interviewRepository).findById(1L);

            Interview returnedInterview = interviewMapper.dtoToEntity(interviewService.getInterviewById(1L));

            Assertions.assertEquals(returnedInterview, interview);
        }

        @Test
        @DisplayName("When Interview does not exist Then EnEntityNotFoundException is thrown")
        public void getInterviewByIdNotFound() {
            doReturn(Optional.empty()).when(interviewRepository).findById(777L);

            Assertions.assertThrows(EntityNotFoundException.class, () -> interviewService.getInterviewById(777L));
        }
    }

    @Nested
    @DisplayName("Check Interview's updateById method")
    class testUpdateByIdMethod {

        @Test
        @DisplayName("When Interview exists Then update it")
        public void updateInterviewById() {
            Interview interview = interviewTestData.getTechInterview();
            Interview updatedInterview = interviewTestData.getHrInterview();

            doReturn(Optional.of(updatedInterview)).when(interviewRepository).findById(1L);

            interviewService.updateInterviewById(1L, interviewMapper.entityToDto(updatedInterview));

            Assertions.assertEquals(interviewService.getInterviewById(1L), interviewMapper.entityToDto(updatedInterview));
        }

        @Test
        @DisplayName("When Interview to update does not exist Then EntityNotFoundException is thrown")
        public void updateInterviewByIdNotFound() {
            Interview interview = interviewTestData.getTechInterview();
            Interview updatedInterview = interviewTestData.getHrInterview();

            doReturn(Optional.of(updatedInterview)).when(interviewRepository).findById(1L);

            Assertions.assertThrows(EntityNotFoundException.class, () -> interviewService.updateInterviewById(777L, interviewMapper.entityToDto(updatedInterview)));
        }
    }

    @Nested
    @DisplayName("Check Interview's getByInterviewerId method")
    class testGetByInterviewerIdMethod {

        @Test
        @DisplayName("When Interviews of this Interviewers exist Then return list of them")
        public void getAllByInterviewerId() {
            Interview techInterview = interviewTestData.getTechInterview();
            Interview hrInterview = interviewTestData.getHrInterview();
            List<Interview> interviews = List.of(techInterview, hrInterview);

            doReturn(interviews).when(interviewRepository).findAllByInterviewerId(1L);

            List<Interview> foundInterviews = interviewService.getAllByInterviewerId(1L)
                    .stream().map(interviewMapper::dtoToEntity)
                    .collect(Collectors.toList());

            Assertions.assertEquals(foundInterviews, interviews);
        }
    }

    @Nested
    @DisplayName("Check Interview's getAll method")
    class testGetAllMethod {

        @Test
        @DisplayName("When Interviews exist Then return list of them")
        public void getAll() {
            Interview techInterview = interviewTestData.getTechInterview();
            Interview hrInterview = interviewTestData.getHrInterview();
            List<Interview> interviews = List.of(techInterview, hrInterview);

            doReturn(interviews).when(interviewRepository).findAll();

            List<Interview> foundInterviews = interviewService.getAll()
                    .stream().map(interviewMapper::dtoToEntity)
                    .collect(Collectors.toList());

            Assertions.assertEquals(foundInterviews, interviews);
        }
    }
}

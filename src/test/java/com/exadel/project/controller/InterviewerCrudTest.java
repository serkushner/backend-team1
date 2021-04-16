package com.exadel.project.controller;

import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.interviewer.entity.InterviewerTestData;
import com.exadel.project.interviewer.repository.InterviewerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("CRUD tests with Interviewer's Entity")
public class InterviewerCrudTest {

    @MockBean
    private InterviewerRepository interviewerRepository;

    @Autowired
    private InterviewerTestData interviewerTestData;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("Check Interviewer's getById method")
    class testGetByIdMethod {

        @Test
        @DisplayName("When Interviewer is exist Then return him from database")
        void getInterviewerById() throws Exception {

            Interviewer interviewer = interviewerTestData.getTechInterviewer();

            doReturn(Optional.of(interviewer)).when(interviewerRepository).findById(1L);

            MvcResult result = mockMvc.perform(get("/interviewer/{id}", 1))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            Interviewer returnedInterviewer = objectMapper.readValue(content, Interviewer.class);

            Assertions.assertEquals(returnedInterviewer, interviewer);
        }


        @Test
        @DisplayName("When Interviewer does't exist Then response status - Entity not found")
        void testInterviewerByIdNotFound() throws Exception {

            doReturn(Optional.empty()).when(interviewerRepository).findById(1358L);

            mockMvc.perform(get("/interviewer/{id}", 1358))
                    .andExpect(status().isNotFound());
        }

    }


    @Nested
    @DisplayName("Check Interviewer's getAll method")
    class testGetAllMethod {

        @Test
        @DisplayName("When Interviewers exist Then return array of them ")
        void getAllInterviewers() throws Exception {

            Interviewer firstInterviewer = interviewerTestData.getTechInterviewer();
            Interviewer secondInterviewer = interviewerTestData.getHrInterviewer();

            doReturn(Arrays.asList(firstInterviewer, secondInterviewer)).when(interviewerRepository).findAll(Sort.by(Sort.Direction.DESC, "type"));

            MvcResult result = mockMvc.perform(get("/interviewer"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            String content = result.getResponse().getContentAsString();

            List<Interviewer> interviewerList = objectMapper.readValue(content, new TypeReference<List<Interviewer>>() {
            });

            Assertions.assertEquals(interviewerList.get(0), firstInterviewer);
            Assertions.assertEquals(interviewerList.get(1), secondInterviewer);
        }

    }


    @Nested
    @DisplayName("Check Interviewer's create method")
    class testCreateInterviewerMethod {

        @Test
        @DisplayName("When Interviewer doesn't exist in database Then save and return him ")
        void testCreateInterviewer() throws Exception {

            Interviewer interviewer = interviewerTestData.getTechInterviewer();
            Interviewer savedInterviewer = interviewerTestData.getTechInterviewer();
            savedInterviewer.setId(null);

            doReturn(interviewer).when(interviewerRepository).save(savedInterviewer);

            MvcResult result = mockMvc.perform(post("/interviewer")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(savedInterviewer)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            Interviewer returnedInterviewer = objectMapper.readValue(content, Interviewer.class);

            Assertions.assertEquals(returnedInterviewer, interviewer);

        }

    }

    @Nested
    @DisplayName("Check Interviewer's update method")
    class testUpdateInterviewerMethod {

        @Test
        @DisplayName("When Interviewer exist Then save Interviewer with updates and return him")
        void testUpdateInterviewer() throws Exception {
            Interviewer interviewer = interviewerTestData.getTechInterviewer();
            Interviewer updateInterviewer = interviewerTestData.getHrInterviewer();
            updateInterviewer.setId(1L);

            doReturn(Optional.of(interviewer)).when(interviewerRepository).findById(1L);
            doReturn(updateInterviewer).when(interviewerRepository).save(updateInterviewer);

            MvcResult result = mockMvc.perform(put("/interviewer/{id}", 1)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateInterviewer)))
                    .andExpect(status().isOk())
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            Interviewer returnedInterviewer = objectMapper.readValue(content, Interviewer.class);

            Assertions.assertEquals(returnedInterviewer,updateInterviewer);
            Assertions.assertNotEquals(returnedInterviewer,interviewer);

        }

        @Test
        @DisplayName("When updated Interviewer doesn't exist Then response back status is Not Found")
        void testUpdateInterviewerNotFound() throws Exception {

            Interviewer interviewerToPut = interviewerTestData.getHrInterviewer();

            doReturn(Optional.of(interviewerToPut)).when(interviewerRepository).findById(2L);

            mockMvc.perform(put("/interviewer/{id}", 135)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(interviewerToPut)))
                    .andExpect(status().isNotFound());

        }

    }

}

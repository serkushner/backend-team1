package com.exadel.project.controller;

import com.exadel.project.internship.dto.InterviewerDto;
import com.exadel.project.internship.entity.InterviewerType;
import com.exadel.project.internship.service.InterviewerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InterviewerRestControllerTests {

    @MockBean
    private InterviewerService interviewerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getInterviewerById() throws Exception {
        InterviewerDto dto = new InterviewerDto();
        dto.setId(1L);
        dto.setName("firstName");
        dto.setSurname("lastName");
        dto.setEmail("test@mail.ru");
        dto.setPhone("80556958574");
        dto.setType(InterviewerType.TECH);
        dto.setSkype("testSkype");

        doReturn(dto).when(interviewerService).getById(1L);

        mockMvc.perform(get("/interviewer/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("firstName")))
                .andExpect(jsonPath("$.surname", is("lastName")))
                .andExpect(jsonPath("$.email", is("test@mail.ru")))
                .andExpect(jsonPath("$.phone", is("80556958574")))
                .andExpect(jsonPath("$.type", is("TECH")))
                .andExpect(jsonPath("$.skype", is("testSkype")));
    }

    @Test
    void testCreateInterviewer() throws Exception {
        InterviewerDto dto = new InterviewerDto();
        dto.setId(5L);
        dto.setName("testName");
        dto.setSurname("testSurname");
        dto.setEmail("test_2@mail.ru");
        dto.setPhone("80565228585");
        dto.setType(InterviewerType.TECH);
        dto.setSkype("test2Skype");

        doReturn(dto).when(interviewerService).createInterviewer(any());

        mockMvc.perform(post("/interviewer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.name", is("testName")))
                .andExpect(jsonPath("$.surname", is("testSurname")))
                .andExpect(jsonPath("$.email", is("test_2@mail.ru")))
                .andExpect(jsonPath("$.phone", is("80565228585")))
                .andExpect(jsonPath("$.type", is("TECH")))
                .andExpect(jsonPath("$.skype", is("test2Skype")));

    }


    private static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

}

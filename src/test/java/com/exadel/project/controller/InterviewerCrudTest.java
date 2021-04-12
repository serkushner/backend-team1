package com.exadel.project.controller;

import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.interviewer.entity.InterviewerTestData;
import com.exadel.project.interviewer.repository.InterviewerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InterviewerCrudTest {

    @MockBean
    private InterviewerRepository interviewerRepository;

    @Autowired
    private InterviewerTestData interviewerTestData;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getInterviewerById() throws Exception {

        Interviewer interviewer = interviewerTestData.getTechInterviewer();

        doReturn(Optional.of(interviewer)).when(interviewerRepository).findById(1L);

        mockMvc.perform(get("/interviewer/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Vladimir")))
                .andExpect(jsonPath("$.surname", is("Mashkov")))
                .andExpect(jsonPath("$.email", is("vlad.mashkov@gmail.com")))
                .andExpect(jsonPath("$.phone", is("80554445588")))
                .andExpect(jsonPath("$.type", is("TECH")))
                .andExpect(jsonPath("$.skype", is("Vlad_tech")));
    }

    @Test
    void getAllInterviewers() throws Exception {

        Interviewer firstInterviewer = interviewerTestData.getTechInterviewer();
        Interviewer secondInterviewer = interviewerTestData.getHrInterviewer();

        doReturn(Arrays.asList(firstInterviewer,secondInterviewer)).when(interviewerRepository).findAll(Sort.by(Sort.Direction.DESC,"type"));

        mockMvc.perform(get("/interviewer"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Vladimir")))
                .andExpect(jsonPath("$[0].surname", is("Mashkov")))
                .andExpect(jsonPath("$[0].email", is("vlad.mashkov@gmail.com")))
                .andExpect(jsonPath("$[0].phone", is("80554445588")))
                .andExpect(jsonPath("$[0].type", is("TECH")))
                .andExpect(jsonPath("$[0].skype", is("Vlad_tech")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Yulia")))
                .andExpect(jsonPath("$[1].surname", is("Serebrennikova")))
                .andExpect(jsonPath("$[1].email", is("yulia_serebro@gmail.com")))
                .andExpect(jsonPath("$[1].phone", is("80559995522")))
                .andExpect(jsonPath("$[1].type", is("HR")))
                .andExpect(jsonPath("$[1].skype", is("Yulia_hr")));

    }

    @Test
    void testCreateInterviewer() throws Exception {

        Interviewer interviewer = interviewerTestData.getTechInterviewer();

        doReturn(interviewer).when(interviewerRepository).save(interviewer);

        mockMvc.perform(post("/interviewer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(interviewer)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Vladimir")))
                .andExpect(jsonPath("$.surname", is("Mashkov")))
                .andExpect(jsonPath("$.email", is("vlad.mashkov@gmail.com")))
                .andExpect(jsonPath("$.phone", is("80554445588")))
                .andExpect(jsonPath("$.type", is("TECH")))
                .andExpect(jsonPath("$.skype", is("Vlad_tech")));

    }


    private static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

}

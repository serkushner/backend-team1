package com.exadel.project.integration.interviewer.controller;

import com.exadel.project.integration.CommonITContext;
import com.exadel.project.integration.interviewer.testentity.InterviewerTestDataDb;
import com.exadel.project.interviewer.dto.InterviewerResponseDTO;
import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.subject.entity.Subject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DisplayName("Integration CRUD tests with MySQL test container")
public class InterviewerIntegrationCrudTest extends CommonITContext {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InterviewerTestDataDb testDataDb;

    @Nested
    @DisplayName("Check Interviewer's getById method with MySQL test container")
    class testGetByIdRequest {

        @Test
        @DisplayName("When HR Interviewer is exist Then return him from database")
        void getHrInterviewerById() throws Exception {

            InterviewerResponseDTO interviewer = testDataDb.getHrInterviewerResponseDTO();

            MvcResult result = mockMvc.perform(get("/interviewer/{id}", 1))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            InterviewerResponseDTO interviewerFromDb = objectMapper.readValue(content, InterviewerResponseDTO.class);

            Assertions.assertEquals(interviewer, interviewerFromDb);
        }

        @Test
        @DisplayName("When TECH Interviewer is exist Then return him from database")
        void getTechInterviewerById() throws Exception {
            InterviewerResponseDTO interviewer = testDataDb.getTechInterviewerResponseDTO();

            MvcResult result = mockMvc.perform(get("/interviewer/{id}", 2))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            InterviewerResponseDTO interviewerFromDb = objectMapper.readValue(content, InterviewerResponseDTO.class);

            Assertions.assertEquals(interviewer, interviewerFromDb);


        }

        @Test
        @DisplayName("When Interviewer does't exist Then response status - Entity not found")
        void getInterviewerByIdNotFound() throws Exception {

            mockMvc.perform(get("/interviewer/{id}", 1000))
                    .andExpect(status().isNotFound());

        }

    }

    @Nested
    @DisplayName("Check Interviewer's getAll method with MySQL test container")
    class testGetAllRequest {

        @Test
        @DisplayName("When Interviewers are exists Then return array of them")
        void getAllInterviewers() throws Exception {

            MvcResult result = mockMvc.perform(get("/interviewer"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            String content = result.getResponse().getContentAsString();

            List<InterviewerResponseDTO> interviewerList = objectMapper.readValue(content, new TypeReference<List<InterviewerResponseDTO>>() {
            });

            Assertions.assertEquals(10, interviewerList.size());
            for (InterviewerResponseDTO dto : interviewerList) {
                Assertions.assertNotNull(dto);
            }
        }
    }

    @Nested
    @DisplayName("Check Interviewer's create method")
    class testCreateRequest {

        @Test
        @DisplayName("When Interviewer doesn't exist in database Then save and return him")
        void createInterviewer(){

        }


    }

    @Nested
    @DisplayName("Check Interviewer's update method")
    class testUpdateRequest {

        @Test
        @DisplayName("When Interviewer exist Then save Interviewer with updates and return him")
        void updateInterviewer(){

        }


    }




}

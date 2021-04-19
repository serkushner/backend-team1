package com.exadel.project.trainee.controller;

import com.exadel.project.internship.entity.Country;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.repository.CountryRepository;
import com.exadel.project.internship.service.InternshipService;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.InterviewPeriod;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.entity.TraineeTestData;
import com.exadel.project.trainee.repository.AdditionalInfoRepository;
import com.exadel.project.trainee.repository.InterviewPeriodRepository;
import com.exadel.project.trainee.repository.TraineeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//TODO test additionalInfoService
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("CRUD tests with Trainee's Entity")
public class TraineeCrudTest {

    @MockBean
    private AdditionalInfoRepository additionalInfoRepository;

    @MockBean
    private InterviewPeriodRepository interviewPeriodRepository;

    @MockBean
    private CountryRepository countryRepository;

    @MockBean
    private TraineeRepository traineeRepository;

    @MockBean
    private InternshipService internshipService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TraineeTestData traineeTestData;

    @Nested
    @DisplayName("Check Trainee's create method")
    class testCreateTraineeMethod{

        @Test
        @DisplayName("When Trainee doesn't exist in database Then save and return him ")
        void testCreateTrainee() throws Exception{
            TraineeDTO traineeDTO = traineeTestData.getTestTraineeDto();
            TraineeDTO savedTraineeDto = traineeTestData.getTestTraineeDto();
            savedTraineeDto.setId(null);
            savedTraineeDto.setTraineeStatus(null);
            Country belarus = traineeTestData.getTestCountry();
            Trainee trainee = traineeTestData.getTestTrainee();
            Trainee savedTrainee = traineeTestData.getTestTrainee();
            savedTrainee.setId(null);
            InterviewPeriod interviewPeriod = traineeTestData.getTestInterviewPeriod();
            InterviewPeriod savedInterviewPeriod = traineeTestData.getTestInterviewPeriod();
            savedInterviewPeriod.setId(null);
            AdditionalInfo additionalInfo = traineeTestData.getTestAdditionalInfo();
            Internship internship = traineeTestData.getTestInternship();

            doReturn(null).when(traineeRepository).findTraineeByEmail(traineeDTO.getEmail());
            doReturn(internship).when(internshipService).getEntityById(1L);
            doReturn(null).when(additionalInfoRepository).findAdditionalInfoByInternshipAndTrainee(null, null);
            doReturn(belarus).when(countryRepository).findCountryByName("Belarus");
            doReturn(trainee).when(traineeRepository).save(savedTrainee);
            doReturn(interviewPeriod).when(interviewPeriodRepository).save(savedInterviewPeriod);
            doReturn(additionalInfo).when(additionalInfoRepository).save(any());


            MvcResult result = mockMvc.perform(post("/internship/1/registration")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(savedTraineeDto)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            TraineeDTO returnedTraineeDto = objectMapper.readValue(content, TraineeDTO.class);
            returnedTraineeDto.getDates().get(0).put("time", returnedTraineeDto.getDates().get(0).get("time").replaceAll(":", "."));
            Assertions.assertEquals(traineeDTO, returnedTraineeDto);
        }
    }
}

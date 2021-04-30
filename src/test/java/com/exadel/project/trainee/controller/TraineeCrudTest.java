package com.exadel.project.trainee.controller;

import com.exadel.project.country.entity.Country;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.country.repository.CountryRepository;
import com.exadel.project.internship.service.InternshipService;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.dto.TraineeToAdminDTO;
import com.exadel.project.trainee.dto.TraineeToAdminDetailsDTO;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.InterviewPeriod;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.entity.TraineeTestData;
import com.exadel.project.trainee.repository.AdditionalInfoRepository;
import com.exadel.project.trainee.repository.InterviewPeriodRepository;
import com.exadel.project.trainee.repository.TraineeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
    class testCreateTraineeClass {

        @Test
        @DisplayName("When Trainee doesn't exist in database Then save and return him ")
        void testCreateTrainee() throws Exception{
            TraineeDTO responseTraineeDTO = traineeTestData.getResponseTestTraineeDto();
            TraineeDTO requestTraineeDto = traineeTestData.getRequestTestTraineeDto();
            Country belarus = traineeTestData.getTestCountry();
            Trainee responseTrainee = traineeTestData.getResponseTestTrainee();
            Trainee requestTrainee = traineeTestData.getRequestTestTrainee();
            InterviewPeriod responseInterviewPeriod = traineeTestData.getResponseTestInterviewPeriod();
            InterviewPeriod requestInterviewPeriod = traineeTestData.getRequestInterviewPeriod();
            AdditionalInfo additionalInfo = traineeTestData.getTestAdditionalInfo();
            Internship internship = traineeTestData.getTestInternship();

            doReturn(null).when(traineeRepository).findTraineeByEmail(responseTraineeDTO.getEmail());
            doReturn(internship).when(internshipService).getEntityById(1L);
            doReturn(null).when(additionalInfoRepository).findAdditionalInfoByInternshipAndTrainee(null, null);
            doReturn(belarus).when(countryRepository).findCountryByName("Belarus");
            doReturn(responseTrainee).when(traineeRepository).save(requestTrainee);
            doReturn(responseInterviewPeriod).when(interviewPeriodRepository).save(requestInterviewPeriod);
            doReturn(additionalInfo).when(additionalInfoRepository).save(any());

            MvcResult result = mockMvc.perform(post("/internship/1/registration")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestTraineeDto)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            TraineeDTO returnedTraineeDto = objectMapper.readValue(content, TraineeDTO.class);
            returnedTraineeDto.getDates().get(0).put("time", returnedTraineeDto.getDates().get(0).get("time").replaceAll(":", "."));
            Assertions.assertEquals(responseTraineeDTO, returnedTraineeDto);
        }
    }

    @Nested
    @DisplayName("Check Trainee's getActualTraineesRegistrations method")
    class testGetActualTraineesRegistrationsClass{

        @Test
        @DisplayName("When Trainee's actual registrations exist Then return list of them")
        void getActualTraineesRegistrationsTest() throws Exception {
            AdditionalInfo additionalInfo = traineeTestData.getTestAdditionalInfo();
            List<AdditionalInfo> additionalInfoList = new ArrayList<>();
            additionalInfoList.add(additionalInfo);
            TraineeToAdminDTO traineeToAdminDTO = traineeTestData.getTestTraineeToAdminDTO();

            doReturn(additionalInfoList).when(additionalInfoRepository).findAll(any(Specification.class), any(Sort.class));

            MvcResult result = mockMvc.perform(get("/trainee"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            List<TraineeToAdminDTO> returnedTraineeToAdminDTOList = objectMapper.readValue(content, new TypeReference<List<TraineeToAdminDTO>>() {
            });

            Assertions.assertEquals(returnedTraineeToAdminDTOList.get(0), traineeToAdminDTO);
        }
    }

    @Nested
    @DisplayName("Check Trainee's additional info getById method")
    class testGetTraineeAdditionalInfoById{

        @Test
        @DisplayName("When Trainee's additional info is exist Then return it from database")
        void getTraineeAdditionalInfoByIdTest() throws Exception {
            TraineeToAdminDetailsDTO traineeToAdminDetailsDTO = traineeTestData.getTestTraineeToAdminDetailsDTO();
            AdditionalInfo additionalInfo = traineeTestData.getTestAdditionalInfo();

            doReturn(Optional.of(additionalInfo)).when(additionalInfoRepository).findById(1L);

            MvcResult result = mockMvc.perform(get("/trainee/ai/{id}", 1))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            TraineeToAdminDetailsDTO returnedTraineeToAdminDetailsDTO = objectMapper.readValue(content, TraineeToAdminDetailsDTO.class);

            Assertions.assertEquals(traineeToAdminDetailsDTO, returnedTraineeToAdminDetailsDTO);
        }

        @Test
        @DisplayName("When trainee's additional info doesn't exist Then response status - Entity not found")
        void testTraineeAdditionalInfoByIdNotFound() throws Exception {

            doReturn(Optional.empty()).when(additionalInfoRepository).findById(13L);

            mockMvc.perform(get("/trainee/ai/{id}", 13))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("Check Trainee's update method")
    class testTraineeUpdateClass{
        @Test
        @DisplayName("When Trainee exist Then save Trainee with updates and return him")
        void testUpdateTrainee() throws Exception {
            Trainee existTrainee = traineeTestData.getResponseTestTrainee();
            Trainee updateTrainee = traineeTestData.getTestUpdateTrainee();
            TraineeDTO updateTraineeDTO = traineeTestData.getTestUpdateTraineeDTO();
            InterviewPeriod interviewPeriod = traineeTestData.getResponseTestInterviewPeriod();
            AdditionalInfo additionalInfo = traineeTestData.getTestAdditionalInfo();

            doReturn(Optional.of(existTrainee)).when(traineeRepository).findById(1L);
            doReturn(updateTrainee).when(traineeRepository).save(updateTrainee);
            doReturn(interviewPeriod).when(interviewPeriodRepository).findByDayOfWeekAndAndStartTimeAndEndTime(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(13, 0));
            doReturn(Optional.of(additionalInfo)).when(additionalInfoRepository).findById(1L);
            doReturn(additionalInfo).when(additionalInfoRepository).save(additionalInfo);

            MvcResult result = mockMvc.perform(put("/trainee/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateTraineeDTO)))
                    .andExpect(status().isOk())
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            TraineeDTO returnedTraineeDTO = objectMapper.readValue(content, TraineeDTO.class);
            returnedTraineeDTO.getDates().get(0).put("time", returnedTraineeDTO.getDates().get(0).get("time").replaceAll(":", "."));

            Assertions.assertEquals(updateTraineeDTO, returnedTraineeDTO);
        }

        @Test
        @DisplayName("When updated Trainee doesn't exist Then response back status is Not Found")
        void testUpdateTraineeNotFound() throws Exception {

            Trainee existTrainee = traineeTestData.getResponseTestTrainee();
            TraineeDTO updateTraineeDTO = traineeTestData.getTestUpdateTraineeDTO();

            doReturn(Optional.of(existTrainee)).when(traineeRepository).findById(1L);

            mockMvc.perform(put("/trainee/{id}", 13)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateTraineeDTO)))
                    .andExpect(status().isNotFound());
        }
    }
}

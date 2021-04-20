package com.exadel.project.administrator.controller;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.administrator.mapper.AdministratorMapper;
import com.exadel.project.administrator.repository.AdministratorRepository;
import com.exadel.project.administrator.testentity.AdministratorTestData;
import com.exadel.project.administrator.testentity.AdministratorTestDataDto;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("CRUD tests with Administrator's Entity")
public class AdministratorCrudTest {

    @MockBean
    private AdministratorRepository administratorRepository;

    @MockBean
    private AdministratorMapper administratorMapper;

    @Autowired
    private AdministratorTestData administratorTestData;

    @Autowired
    private AdministratorTestDataDto administratorTestDataDto;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("Check Administrator's getById method")
    class testGetByIdMethod {

        @Test
        @DisplayName("When Administrator is exist Then return him from database")
        void getAdministratorById() throws Exception {

            Administrator administrator = administratorTestData.getAdministrator(1L, "Neo");
            AdministratorDto administratorDto = administratorTestDataDto.getAdministratorDto(1L, "Neo");

            doReturn(administratorDto).when(administratorMapper).entityToDto(administrator);
            doReturn(Optional.of(administrator)).when(administratorRepository).findById(1L);

            MvcResult result = mockMvc.perform(get("/admin/{id}", 1))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            AdministratorDto returnedAdministrator = objectMapper.readValue(content, AdministratorDto.class);

            Assertions.assertEquals(returnedAdministrator, administratorDto);
        }

        @Test
        @DisplayName("When Interviewer does't exist Then response status - Entity not found")
        void testAdministratorByIdNotFound() throws Exception {

            doReturn(Optional.empty()).when(administratorRepository).findById(1358L);

            mockMvc.perform(get("/admin/{id}", 1358))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("Check Administrator's getAll method")
    class testGetAllMethod {

        @Test
        @DisplayName("When Administrators exist Then return array of them ")
        void getAllInterviewers() throws Exception {

            AdministratorDto administratorDto = administratorTestDataDto.getAdministratorDto(1L, "Neo");
            AdministratorDto administratorSecondDto = administratorTestDataDto.getAdministratorDto(2L, "Smith");
            Administrator firstAdministrator = administratorTestData.getAdministrator(1L, "Neo");
            Administrator secondAdministrator = administratorTestData.getAdministrator(2L, "Smith");

            doReturn(administratorDto).when(administratorMapper).entityToDto(firstAdministrator);
            doReturn(administratorSecondDto).when(administratorMapper).entityToDto(secondAdministrator);

            doReturn(Arrays.asList(firstAdministrator, secondAdministrator)).when(administratorRepository).findAll(Sort.by(Sort.Direction.ASC, "surname"));

            MvcResult result = mockMvc.perform(get("/admin"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            String content = result.getResponse().getContentAsString();

            List<AdministratorDto> administratorList = objectMapper.readValue(content, new TypeReference<>() {
            });

            Assertions.assertEquals(administratorList.get(0), administratorDto);
            Assertions.assertEquals(administratorList.get(1), administratorSecondDto);
        }
    }

    @Nested
    @DisplayName("Check Administrator's create method")
    class testCreateAdministratorMethod {

        @Test
        @DisplayName("When Administrator doesn't exist in database Then save and return him ")
        void testCreateAdministrator() throws Exception {

            Administrator administrator = administratorTestData.getAdministrator(1L, "Neo");
            Administrator savedAdministrator = administratorTestData.getAdministrator(1L, "Neo");
            AdministratorDto administratorDto = administratorTestDataDto.getAdministratorDto(1L, "Neo");
            AdministratorDto savedAdministratorDto = administratorTestDataDto.getAdministratorDto(1L, "Neo");
            savedAdministrator.setId(null);
            savedAdministratorDto.setId(null);
            savedAdministrator.setLogin("Neo");
            savedAdministratorDto.setLogin("Neo");

            doReturn(administratorDto).when(administratorMapper).entityToDto(administrator);
            doReturn(savedAdministrator).when(administratorMapper).dtoToEntity(savedAdministratorDto);
            doReturn(administratorDto).when(administratorMapper).entityToDto(savedAdministrator);
            doReturn(administrator).when(administratorRepository).save(savedAdministrator);

            MvcResult result = mockMvc.perform(post("/admin")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(savedAdministrator)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            AdministratorDto returnedAdministrator = objectMapper.readValue(content, AdministratorDto.class);

            Assertions.assertEquals(returnedAdministrator, administratorDto);

        }
    }

    @Nested
    @DisplayName("Check Administrator's update method")
    class testUpdateAdministratorMethod {

        @Test
        @DisplayName("When Administrator exist Then save Administrator with updates and return him")
        void testUpdateAdministrator() throws Exception {
            Administrator administrator = administratorTestData.getAdministrator(1L, "Neo");
            AdministratorDto administratorDto = administratorTestDataDto.getAdministratorDto(1L, "Neo");
            AdministratorDto updateAdministratorDto = administratorTestDataDto.getAdministratorDto(2L, "Smith");
            Administrator updateAdministrator = administratorTestData.getAdministrator(2L, "Smith");
            updateAdministrator.setId(1L);
            updateAdministratorDto.setId(1L);

            doReturn(updateAdministratorDto).when(administratorMapper).entityToDto(administrator);
            doReturn(updateAdministrator).when(administratorMapper).dtoToEntity(updateAdministratorDto);
            doReturn(updateAdministratorDto).when(administratorMapper).entityToDto(updateAdministrator);

            doReturn(Optional.of(administrator)).when(administratorRepository).findById(1L);
            doReturn(updateAdministrator).when(administratorRepository).save(administrator);

            MvcResult result = mockMvc.perform(put("/admin/{id}", 1)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateAdministrator)))
                    .andExpect(status().isOk())
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            AdministratorDto returnedAdministrator = objectMapper.readValue(content, AdministratorDto.class);

            Assertions.assertEquals(returnedAdministrator, updateAdministratorDto);
            Assertions.assertNotEquals(returnedAdministrator, administratorDto);

        }

        @Test
        @DisplayName("When updated Administrator doesn't exist Then response back status is Not Found")
        void testUpdateAdministratorNotFound() throws Exception {

            Administrator administratorToPut = administratorTestData.getAdministrator(1L, "Neo");

            doReturn(Optional.of(administratorToPut)).when(administratorRepository).findById(2L);

            mockMvc.perform(put("/admin/{id}", 135)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(administratorToPut)))
                    .andExpect(status().isNotFound());

        }

    }

    @Nested
    @DisplayName("Check Administrator's delete method")
    class testDeleteAdministratorMethod {

        @Test
        @DisplayName("When Administrator exist Then delete Administrator")
        void testDeleteAdministrator() throws Exception {
            Administrator administrator = administratorTestData.getAdministrator(1L, "Neo");
            doReturn(Optional.of(administrator)).when(administratorRepository).findById(1L);
            mockMvc.perform(
                    delete("/admin/{id}", 1L))
                    .andExpect(status().isNoContent());
        }
    }
}

package com.exadel.project.administrator.controller;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.administrator.mapper.AdministratorMapper;
import com.exadel.project.administrator.service.AdministratorService;
import com.exadel.project.administrator.testentity.AdministratorTestData;
import com.exadel.project.administrator.repository.AdministratorRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("CRUD tests with Administrator's Entity")
public class AdministratorCrudTest {

    @MockBean
    private AdministratorRepository administratorRepository;

    @MockBean
    private AdministratorService administratorService;

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
        @DisplayName("When Administrator exists then return him from database")
        void getAdministratorById() throws Exception {

            Administrator administrator = administratorTestData.getAdministrator();

            //AdministratorDto administratorDto = administratorTestDataDto.getAdministratorDto();
            doReturn(Optional.of(administrator)).when(administratorRepository).findById(1L);
            //doReturn(administratorDto).when(administratorMapper).entityToDto(administrator);

            MvcResult result = mockMvc.perform(get("/admin/{id}", 1))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            Administrator returnedAdministrator = objectMapper.readValue(content, Administrator.class);

            Assertions.assertEquals(returnedAdministrator, administrator);
        }

        @Test
        @DisplayName("When Administrator does not exist then response status - Entity not found")
        void testAdministratorByIdNotFound() throws Exception {

            doReturn(Optional.empty()).when(administratorRepository).findById(5L);

            mockMvc.perform(get("/admin/{id}", 5))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("Check Administrator's getAll method")
    class testGetAllMethod {

        @Test
        @DisplayName("When Administrators exist Then return array of them ")
        void getAllAdministrators() throws Exception {

            AdministratorDto administratorDto = administratorTestDataDto.getAdministratorDto();
            AdministratorDto administratorSecondDto = administratorTestDataDto.getSecondAdministratorDto();
            Administrator firstAdministrator = administratorTestData.getAdministrator();
            Administrator secondAdministrator = administratorTestData.getSecondAdministrator();

            doReturn(administratorDto).when(administratorMapper).entityToDto(firstAdministrator);
            doReturn(administratorSecondDto).when(administratorMapper).entityToDto(secondAdministrator);
            doReturn(Arrays.asList(firstAdministrator, secondAdministrator)).when(administratorRepository).findAll(Sort.by(Sort.Direction.DESC, "surname"));

            MvcResult result = mockMvc.perform(get("/admin"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();

            String content = result.getResponse().getContentAsString();

            List<Administrator> administratorList = objectMapper.readValue(content, new TypeReference<>() {
            });

            Assertions.assertEquals(administratorList.get(0), firstAdministrator);
            Assertions.assertEquals(administratorList.get(1), secondAdministrator);
        }
    }

    @Nested
    @DisplayName("Check Administrator's add method")
    class testAddAdministratorMethod {

        @Test
        @DisplayName("When Administrator doesn't exist in database Then save and return him ")
        void testAddAdministrator() throws Exception {

            Administrator administrator = administratorTestData.getAdministrator();
            AdministratorDto savedAdministratorDto = administratorTestDataDto.getAdministratorDto();
            Administrator savedAdministrator = administratorTestData.getAdministrator();
            savedAdministrator.setId(null);
            savedAdministratorDto.setId(null);
            AdministratorDto administratorDto = administratorTestDataDto.getAdministratorDto();

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
            Administrator returnedAdministrator = objectMapper.readValue(content, Administrator.class);

            Assertions.assertEquals(returnedAdministrator, administrator);
        }
    }

    @Nested
    @DisplayName("Check Administrator's update method")
    class testUpdateAdministratorMethod {

        @Test
        @DisplayName("When Administrator exist Then save Administrator with updates and return him")
        void testUpdateAdministrator() throws Exception {
            Administrator administrator = administratorTestData.getAdministrator();
            AdministratorDto updateAdministratorDto = administratorTestDataDto.getSecondAdministratorDto();
            Administrator updateAdministrator = administratorTestData.getSecondAdministrator();
            updateAdministrator.setId(1L);
            updateAdministratorDto.setId(1L);

            doReturn(updateAdministratorDto).when(administratorMapper).entityToDto(administrator);
            doReturn(updateAdministrator).when(administratorMapper).dtoToEntity(updateAdministratorDto);
            doReturn(updateAdministratorDto).when(administratorMapper).entityToDto(updateAdministrator);

            doReturn(administrator).when(administratorRepository).findAdministratorById(1L);
            doReturn(updateAdministrator).when(administratorRepository).save(administrator);

            MvcResult result = mockMvc.perform(put("/admin/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateAdministrator)))
                    .andExpect(status().isOk())
                    .andReturn();

            String content = result.getResponse().getContentAsString();
            Administrator returnedAdministrator = objectMapper.readValue(content, Administrator.class);

            Assertions.assertEquals(returnedAdministrator, updateAdministrator);
            Assertions.assertNotEquals(returnedAdministrator, administrator);
        }
    }

    @Nested
    @DisplayName("Check Administrator's delete method")
    class testDeleteAdministratorMethod {

        @Test
        @DisplayName("When Administrator exist Then delete Administrator")
        void testDeleteAdministrator() throws Exception {
            Administrator administrator = administratorTestData.getAdministrator();
            doReturn(administrator).when(administratorRepository).findAdministratorById(1L);
            doNothing().when(administratorService).deleteAdministratorById(1L);
            mockMvc.perform(
                    delete("/admin/{id}", 1L))
                    .andExpect(status().isNoContent());
            verify(administratorService, times(1)).deleteAdministratorById(administrator.getId());
            verifyNoMoreInteractions(administratorService);
        }
    }
}


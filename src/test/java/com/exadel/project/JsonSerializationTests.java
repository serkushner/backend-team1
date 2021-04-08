package com.exadel.project;

import com.exadel.project.internship.dto.InterviewerDto;
import com.exadel.project.internship.entity.InterviewerType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JsonTest
public class JsonSerializationTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSerialization() throws Exception {
        InterviewerDto dto = new InterviewerDto();
        dto.setId(1L);
        dto.setName("testName");
        dto.setSurname("testSurname");
        dto.setEmail("test_serialize@mail.ru");
        dto.setPhone("80504527895");
        dto.setType(InterviewerType.TECH);
        dto.setSkype("serializeSkype");

        String json = objectMapper.writeValueAsString(dto);

        assertEquals("{\"id\":1,\"name\":\"testName\",\"surname\":\"testSurname\"," +
                "\"email\":\"test_serialize@mail.ru\",\"phone\":\"80504527895\"," +
                "\"type\":\"TECH\",\"skype\":\"serializeSkype\"}", json);

    }

    @Test
    public void testDeserialization() throws Exception {
        String json = "{\"id\":1,\"name\":\"testName\",\"surname\":\"testSurname\"," +
                "\"email\":\"test_serialize@mail.ru\",\"phone\":\"80504527895\"," +
                "\"type\":\"TECH\",\"skype\":\"serializeSkype\"}";
        InterviewerDto dto = objectMapper.readValue(json, InterviewerDto.class);
        assertEquals(1L, dto.getId());
        assertEquals("testName", dto.getName());
        assertEquals("testSurname", dto.getSurname());
        assertEquals("test_serialize@mail.ru", dto.getEmail());
        assertEquals("80504527895", dto.getPhone());
        assertEquals(InterviewerType.TECH, dto.getType());
        assertEquals("serializeSkype", dto.getSkype());

    }
}

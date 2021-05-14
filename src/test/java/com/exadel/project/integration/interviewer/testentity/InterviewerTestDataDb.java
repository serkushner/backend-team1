package com.exadel.project.integration.interviewer.testentity;

import com.exadel.project.interview.dto.InterviewTimeRequestDTO;
import com.exadel.project.interview.dto.InterviewTimeResponseDTO;
import com.exadel.project.interview.dto.InterviewToInterviewerDTO;
import com.exadel.project.interviewer.dto.InterviewerRequestDTO;
import com.exadel.project.interviewer.dto.InterviewerResponseDTO;
import com.exadel.project.interviewer.entity.InterviewerType;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class InterviewerTestDataDb {

    public InterviewerResponseDTO getHrInterviewerResponseDTO() {
        InterviewerResponseDTO responseDTO = new InterviewerResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setName("Viktor");
        responseDTO.setSurname("Petrov");
        responseDTO.setEmail("vpetr@gmail.com");
        responseDTO.setPhone("80551458526");
        responseDTO.setType(InterviewerType.HR);
        responseDTO.setSkype("Vik25");
        List<String> emptyList = Collections.emptyList();
        List<InterviewToInterviewerDTO> emptyListOfInterview = Collections.emptyList();
        List<InterviewTimeResponseDTO> emptyListOfInterviewTimeResponseDTO = Collections.emptyList();
        responseDTO.setSubjects(emptyList);
        responseDTO.setInterviews(emptyListOfInterview);
        responseDTO.setInterviewTimes(emptyListOfInterviewTimeResponseDTO);
        return responseDTO;
    }

    public InterviewerResponseDTO getTechInterviewerResponseDTO() {
        InterviewerResponseDTO responseDTO = new InterviewerResponseDTO();
        responseDTO.setId(2L);
        responseDTO.setName("Angelina");
        responseDTO.setSurname("Kvok");
        responseDTO.setEmail("kvoka@mail.ru");
        responseDTO.setPhone("80566357485");
        responseDTO.setType(InterviewerType.TECH);
        responseDTO.setSkype("Angel_teh");
        List<InterviewToInterviewerDTO> emptyListOfInterview = Collections.emptyList();
        List<InterviewTimeResponseDTO> emptyListOfInterviewTimeResponseDTO = Collections.emptyList();
        responseDTO.setSubjects(List.of("DevOps"));
        responseDTO.setInterviews(emptyListOfInterview);
        responseDTO.setInterviewTimes(emptyListOfInterviewTimeResponseDTO);
        return responseDTO;
    }

    public InterviewerRequestDTO getTechInterviewerToSaveInDb() {
        InterviewerRequestDTO requestDTO = new InterviewerRequestDTO();
        requestDTO.setName("Sergei");
        requestDTO.setSurname("Vorobei");
        requestDTO.setEmail("serega@gmail.com");
        requestDTO.setPhone("80551257485");
        requestDTO.setType(InterviewerType.TECH);
        requestDTO.setSkype("Serg87");
        requestDTO.setSubjects(List.of("Java"));
        List<InterviewTimeRequestDTO> emptyListOfInterviewerTimeRequestDTO = Collections.emptyList();
        requestDTO.setInterviewTimes(emptyListOfInterviewerTimeRequestDTO);
        return requestDTO;
    }

    public InterviewerRequestDTO getDuplicateInterviewerToSaveInDb() {
        InterviewerRequestDTO requestDTO = new InterviewerRequestDTO();
        requestDTO.setName("Angelina");
        requestDTO.setSurname("Kvok");
        requestDTO.setEmail("kvoka@mail.ru");
        requestDTO.setPhone("80566357485");
        requestDTO.setType(InterviewerType.TECH);
        requestDTO.setSkype("Angel_teh");
        requestDTO.setSubjects(List.of("Java"));
        List<InterviewTimeRequestDTO> emptyListOfInterviewerTimeRequestDTO = Collections.emptyList();
        requestDTO.setInterviewTimes(emptyListOfInterviewerTimeRequestDTO);
        return requestDTO;
    }

    public InterviewerRequestDTO getInterviewerToUpdateInDb() {
        InterviewerRequestDTO requestDTO = new InterviewerRequestDTO();
        requestDTO.setName("Marina");
        requestDTO.setSurname("Orlova");
        requestDTO.setEmail("mary.orlova@yandex.by");
        requestDTO.setPhone("80543658974");
        requestDTO.setType(InterviewerType.TECH);
        requestDTO.setSkype("Masha95");
        requestDTO.setSubjects(List.of("Java"));
        List<InterviewTimeRequestDTO> emptyListOfInterviewerTimeRequestDTO = Collections.emptyList();
        requestDTO.setInterviewTimes(emptyListOfInterviewerTimeRequestDTO);
        return requestDTO;
    }
}

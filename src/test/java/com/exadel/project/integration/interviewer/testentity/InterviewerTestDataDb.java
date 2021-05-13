package com.exadel.project.integration.interviewer.testentity;

import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.dto.InterviewTimeResponseDTO;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.interviewer.dto.InterviewerResponseDTO;
import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.interviewer.entity.InterviewerType;
import com.exadel.project.subject.dto.SubjectDTO;
import com.exadel.project.subject.entity.Subject;
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
        List<InterviewDTO> emptyListOfInterview = Collections.emptyList();
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
        List<InterviewDTO> emptyListOfInterview = Collections.emptyList();
        List<InterviewTimeResponseDTO> emptyListOfInterviewTimeResponseDTO = Collections.emptyList();
        responseDTO.setSubjects(List.of("DevOps"));
        responseDTO.setInterviews(emptyListOfInterview);
        responseDTO.setInterviewTimes(emptyListOfInterviewTimeResponseDTO);
        return responseDTO;
    }

    private SubjectDTO getDevOpsSubject() {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setName("DevOps");
        return subjectDTO;
    }
}

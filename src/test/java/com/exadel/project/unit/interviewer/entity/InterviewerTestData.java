package com.exadel.project.unit.interviewer.entity;

import com.exadel.project.interview.dto.InterviewTimeRequestDTO;
import com.exadel.project.interview.dto.InterviewTimeResponseDTO;
import com.exadel.project.interview.entity.InterviewTime;
import com.exadel.project.interviewer.dto.InterviewerRequestDTO;
import com.exadel.project.interviewer.dto.InterviewerResponseDTO;
import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.interviewer.entity.InterviewerType;
import com.exadel.project.subject.entity.Subject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
public class InterviewerTestData {

    public Interviewer getTechInterviewer() {
        Interviewer interviewer = new Interviewer();
        interviewer.setId(1L);
        interviewer.setName("Vladimir");
        interviewer.setSurname("Mashkov");
        interviewer.setEmail("vlad.mashkov@gmail.com");
        interviewer.setPhone("80554445588");
        interviewer.setType(InterviewerType.TECH);
        interviewer.setSkype("Vlad_tech");
        Subject subject = getTestSubject();
        interviewer.setSubjects(List.of(subject));

        return interviewer;
    }

    public InterviewerResponseDTO getTestTechInterviewerDTO(){
        InterviewerResponseDTO interviewerResponseDTO = new InterviewerResponseDTO();
        interviewerResponseDTO.setId(1L);
        interviewerResponseDTO.setName("Vladimir");
        interviewerResponseDTO.setSurname("Mashkov");
        interviewerResponseDTO.setEmail("vlad.mashkov@gmail.com");
        interviewerResponseDTO.setPhone("80554445588");
        interviewerResponseDTO.setType(InterviewerType.TECH);
        interviewerResponseDTO.setSkype("Vlad_tech");

        String subject = "Java";
        interviewerResponseDTO.setSubjects(List.of(subject));

        InterviewTimeResponseDTO interviewTimeResponseDTO = new InterviewTimeResponseDTO();
        interviewTimeResponseDTO.setStartDate(LocalDateTime.parse("2021-05-01T10:00"));
        interviewTimeResponseDTO.setEndDate(LocalDateTime.parse("2021-05-01T11:00"));
        interviewTimeResponseDTO.setId(1L);
        interviewerResponseDTO.setInterviewTimes(List.of(interviewTimeResponseDTO));

        return interviewerResponseDTO;
    }

    public InterviewerResponseDTO getTestHrInterviewerDTO(){
        InterviewerResponseDTO interviewerResponseDTO = getTestTechInterviewerDTO();
        interviewerResponseDTO.setId(2L);
        interviewerResponseDTO.setName("Yulia");
        interviewerResponseDTO.setSurname("Serebrennikova");
        interviewerResponseDTO.setEmail("yulia_serebro@gmail.com");
        interviewerResponseDTO.setPhone("80559995522");
        interviewerResponseDTO.setType(InterviewerType.HR);
        interviewerResponseDTO.setSkype("Yulia_hr");
        interviewerResponseDTO.setSubjects(Collections.emptyList());
        interviewerResponseDTO.setInterviews(null);
        return interviewerResponseDTO;
    }

    public InterviewerRequestDTO getRequestInterviewerDTO(){
        InterviewerRequestDTO interviewerRequestDTO = new InterviewerRequestDTO();
        InterviewTimeRequestDTO interviewTimeRequestDTO = new InterviewTimeRequestDTO();
        interviewTimeRequestDTO.setStartDate(LocalDateTime.parse("2021-05-01T10:00"));
        interviewerRequestDTO.setInterviewTimes(List.of(interviewTimeRequestDTO));
        interviewerRequestDTO.setEmail("vlad.mashkov@gmail.com");
        interviewerRequestDTO.setPhone("80554445588");
        interviewerRequestDTO.setSurname("Mashkov");
        String subject = "Java";
        interviewerRequestDTO.setSubjects(List.of(subject));
        interviewerRequestDTO.setSkype("Vlad_tech");
        interviewerRequestDTO.setName("Vladimir");
        interviewerRequestDTO.setType(InterviewerType.TECH);
        return interviewerRequestDTO;
    }

    public Subject getTestSubject(){
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Java");
        return subject;
    }

    public Interviewer getHrInterviewer() {
        Interviewer interviewer = new Interviewer();
        interviewer.setId(2L);
        interviewer.setName("Yulia");
        interviewer.setSurname("Serebrennikova");
        interviewer.setEmail("yulia_serebro@gmail.com");
        interviewer.setPhone("80559995522");
        interviewer.setType(InterviewerType.HR);
        interviewer.setSkype("Yulia_hr");
        interviewer.setInterviewTimes(List.of());
        return interviewer;
    }

    public InterviewTime getTechInterviewTime(){
        InterviewTime interviewTime = new InterviewTime();
        interviewTime.setId(1L);
        interviewTime.setStartDate(LocalDateTime.parse("2021-05-01T10:00"));
        interviewTime.setEndDate(LocalDateTime.parse("2021-05-01T11:00"));
        return interviewTime;
    }

    public InterviewTime getHrInterviewTime(){
        InterviewTime interviewTime = new InterviewTime();
        interviewTime.setId(1L);
        interviewTime.setStartDate(LocalDateTime.parse("2021-05-01T10:00"));
        interviewTime.setEndDate(LocalDateTime.parse("2021-05-01T11:00"));
        return interviewTime;
    }
}

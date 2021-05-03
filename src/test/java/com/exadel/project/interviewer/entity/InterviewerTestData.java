package com.exadel.project.interviewer.entity;

import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.dto.InterviewTimeDTO;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.interview.entity.InterviewTime;
import com.exadel.project.interviewer.dto.InterviewerDTO;
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

        Interview interview = new Interview();
        InterviewTime interviewTime = new InterviewTime();
        interviewTime.setEndDate(LocalDateTime.now());
        interview.setName("java tech interview with Vlad");
        interview.setInterviewTime(interviewTime);
        interviewer.setInterviews(List.of(interview));

        return interviewer;
    }

    public InterviewerDTO getTestTechInterviewerDTO(){
        InterviewerDTO interviewerDTO = new InterviewerDTO();
        interviewerDTO.setId(1L);
        interviewerDTO.setName("Vladimir");
        interviewerDTO.setSurname("Mashkov");
        interviewerDTO.setEmail("vlad.mashkov@gmail.com");
        interviewerDTO.setPhone("80554445588");
        interviewerDTO.setType(InterviewerType.TECH);
        interviewerDTO.setSkype("Vlad_tech");

        String subject = "Java";
        interviewerDTO.setSubjects(List.of(subject));

        InterviewDTO interviewDTO = new InterviewDTO();
        InterviewTimeDTO interviewTimeDTO = new InterviewTimeDTO();
        interviewTimeDTO.setEndDate(LocalDateTime.now());
        interviewDTO.setName("java tech interview with Vlad");
        interviewDTO.setInterviewTime(interviewTimeDTO);
        interviewerDTO.setInterviews(List.of(interviewDTO));
        interviewerDTO.setInterviewTimes(Collections.emptyList());

        return interviewerDTO;
    }

    public InterviewerDTO getTestHrInterviewerDTO(){
        InterviewerDTO interviewerDTO = getTestTechInterviewerDTO();
        interviewerDTO.setId(2L);
        interviewerDTO.setName("Yulia");
        interviewerDTO.setSurname("Serebrennikova");
        interviewerDTO.setEmail("yulia_serebro@gmail.com");
        interviewerDTO.setPhone("80559995522");
        interviewerDTO.setType(InterviewerType.HR);
        interviewerDTO.setSkype("Yulia_hr");
        interviewerDTO.setSubjects(Collections.emptyList());
        interviewerDTO.setInterviews(null);
        return interviewerDTO;
    }

    public InterviewerDTO getRequestInterviewerDTO(){
        InterviewerDTO interviewerDTO = getTestTechInterviewerDTO();
        interviewerDTO.setId(null);
        return interviewerDTO;
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
        return interviewer;
    }

}

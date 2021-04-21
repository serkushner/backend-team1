package com.exadel.project.interviewer.entity;

import com.exadel.project.internship.entity.Subject;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.interview.entity.InterviewTime;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class InterviewerTestData {

    public Interviewer getTechInterviewer(){
        Interviewer interviewer = new Interviewer();
        interviewer.setId(1L);
        interviewer.setName("Vladimir");
        interviewer.setSurname("Mashkov");
        interviewer.setEmail("vlad.mashkov@gmail.com");
        interviewer.setPhone("80554445588");
        interviewer.setType(InterviewerType.TECH);
        interviewer.setSkype("Vlad_tech");

        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Java");
        interviewer.setSubjects(List.of(subject));

        Interview interview = new Interview();
        InterviewTime interviewTime = new InterviewTime();
        interviewTime.setEndDate(LocalDateTime.now());
        interview.setName("java tech interview with Vlad");
        interview.setInterviewTime(interviewTime);
        interviewer.setInterviews(List.of(interview));

        return interviewer;
    }

    public Interviewer getHrInterviewer(){
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

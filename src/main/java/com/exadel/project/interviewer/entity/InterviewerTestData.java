package com.exadel.project.interviewer.entity;

import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.interviewer.entity.InterviewerType;
import org.springframework.stereotype.Component;

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

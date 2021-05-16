package com.exadel.project.interview.listener;

import com.exadel.project.interview.event.InterviewCreatedEvent;
import com.exadel.project.interview.service.JwtInterviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class InterviewCreatedEventListener {

    private final JwtInterviewService jwtInterviewService;

    //1st and 2nd interview
    @TransactionalEventListener
    public void onInterviewCreated(InterviewCreatedEvent event){
        String interviewUrl = jwtInterviewService.generateToken(event.getInterview());
        String interviewerEmail = event.getInterview().getInterviewer().getEmail();
        String traineeEmail = event.getInterview().getTrainee().getEmail();
        //TODO send notification to trainee and interviewer
//        String interviewType = event.getInterview().get
        String interviewType = event.getInterview().getInterviewer().getType().toString();
        //event -> (get interview, getin)tech/HR, date, time
        //intenrship -> date, title
    }
}
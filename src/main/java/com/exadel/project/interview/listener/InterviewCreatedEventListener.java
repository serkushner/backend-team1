package com.exadel.project.interview.listener;

import com.exadel.project.interview.event.InterviewCreatedEvent;
import com.exadel.project.interview.service.JwtInterviewService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class InterviewCreatedEventListener {

    private static final Logger logger = LoggerFactory.getLogger(InterviewCreatedEventListener.class);
    private final JwtInterviewService jwtInterviewService;

    @TransactionalEventListener
    public void onInterviewCreated(InterviewCreatedEvent event){
        String interviewUrl = jwtInterviewService.generateToken(event.getInterview());
        System.out.println(interviewUrl);
        String interviewerEmail = event.getInterview().getInterviewer().getEmail();
        String traineeEmail = event.getInterview().getTrainee().getEmail();
        //TODO send notification to trainee and interviewer
    }
}
package com.exadel.project.interview.listener;

import com.exadel.project.common.mailSender.EmailService;
import com.exadel.project.interview.event.InterviewCreatedEvent;
import com.exadel.project.interview.service.JwtInterviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class InterviewCreatedEventListener {

    private final JwtInterviewService jwtInterviewService;
    private final EmailService emailService;

    public InterviewCreatedEventListener(JwtInterviewService jwtInterviewService,
                                         @Qualifier("emailNotificationSender") EmailService emailService) {
        this.jwtInterviewService = jwtInterviewService;
        this.emailService = emailService;
    }

    //1st and 2nd interview
    @TransactionalEventListener
    public void onInterviewCreated(InterviewCreatedEvent event){
        String interviewUrl = jwtInterviewService.generateToken(event.getInterview());
        emailService.sendHTMLInterviewReminderEmailWithFeedback(event.getInterview(), interviewUrl);
        emailService.sendHTMLInterviewReminderEmail(event.getInterview());
    }
}
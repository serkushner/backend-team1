package com.exadel.project.internship.listener;

import com.exadel.project.common.mailSender.EmailService;
import com.exadel.project.internship.event.InternshipPublishedEvent;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.service.TraineeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Component
public class InternshipPublishedEventListener {

    private static final Logger logger = LoggerFactory.getLogger(InternshipPublishedEventListener.class);
    private final EmailService emailService;
    private final TraineeService traineeService;
    @Value("${internship.url}")
    private String internshipBaseUrl;

    @Autowired
    public InternshipPublishedEventListener(@Qualifier("emailNotificationSender") EmailService emailService,
                                            TraineeService traineeService) {
        this.emailService = emailService;
        this.traineeService = traineeService;
    }

    @TransactionalEventListener
    public void onInternshipPublishedEvent(InternshipPublishedEvent event){
        logger.info("published internship, try to send notifications tp emails with same subject or subjects");
        List<String> emails = traineeService.getTraineesEmailsByHistorySubjects(event.getInternship().getSubjects());
        String internshipUrl = internshipBaseUrl + event.getInternship().getId().toString();
        for (String email : emails) {
            Trainee trainee = traineeService.findTraineeByEmail(email);
            emailService.sendHTMLInternshipAnnouncementEmail(event.getInternship(), trainee, internshipUrl);
        }
    }
}
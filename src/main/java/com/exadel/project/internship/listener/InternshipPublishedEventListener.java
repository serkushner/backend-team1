package com.exadel.project.internship.listener;

import com.exadel.project.internship.event.InternshipPublishedEvent;
import com.exadel.project.internship.service.InternshipService;
import com.exadel.project.trainee.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InternshipPublishedEventListener {

    private static final Logger logger = LoggerFactory.getLogger(InternshipPublishedEventListener.class);
    private final TraineeService traineeService;
    private final InternshipService internshipService;

    @TransactionalEventListener
    public void onInternshipPublished(InternshipPublishedEvent event){
        logger.info("published internship, try sent notifications");
        List<String> emails = traineeService.getTraineesEmailsByHistorySubjects(event.getSubjects());
        System.out.println(emails);
        //TODO sent notifications to emails through email-sender
    }
}
package com.exadel.project.common.service;

import com.exadel.project.common.event.InternshipPublishedEvent;
import com.exadel.project.trainee.service.TraineeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Service
@AllArgsConstructor
public class ListenerService {

    private final TraineeService traineeService;

    @TransactionalEventListener
    public void onInternshipPublished(InternshipPublishedEvent event){
        List<String> emails = traineeService.getTraineesEmailsByHistorySubjects(event.getSubjects());
        //TODO sent notifications to emails through email-sender
    }
}
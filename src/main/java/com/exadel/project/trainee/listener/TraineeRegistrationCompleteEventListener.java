package com.exadel.project.trainee.listener;

import com.exadel.project.configurations.JwtConfiguration;
import com.exadel.project.trainee.event.TraineeRegistrationCompleteEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@AllArgsConstructor
public class TraineeRegistrationCompleteEventListener {

    private final JwtConfiguration jwtConfiguration;

    @TransactionalEventListener
    public void onTraineeRegistrationCompleteEvent(TraineeRegistrationCompleteEvent event){
        String approveUrl = jwtConfiguration.generateToken(event.getAdditionalInfo());
        System.out.println(approveUrl);
        //TODO sent notifications to emails through email-sender
    }
}


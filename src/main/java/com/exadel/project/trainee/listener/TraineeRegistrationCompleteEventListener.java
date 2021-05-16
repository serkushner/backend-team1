package com.exadel.project.trainee.listener;

import com.exadel.project.common.mailSender.EmailService;
import com.exadel.project.configurations.JwtConfiguration;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.event.TraineeRegistrationCompleteEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@AllArgsConstructor
public class TraineeRegistrationCompleteEventListener {

    private final JwtConfiguration jwtConfiguration;
    private final EmailService emailService;
    private static final Logger logger = LoggerFactory.getLogger(
            TraineeRegistrationCompleteEventListener.class);

    @TransactionalEventListener
    public void onTraineeRegistrationCompleteEvent(TraineeRegistrationCompleteEvent event){
        String approveUrl = jwtConfiguration.generateToken(event.getAdditionalInfo());
        Trainee trainee = event.getAdditionalInfo().getTrainee();
        Internship internship = event.getAdditionalInfo().getInternship();
        logInfo(approveUrl, trainee, internship);
        emailService.sendHTMLBasedConfirmEmail(internship, trainee, approveUrl);
    }

    private void logInfo(String approveUrl, Trainee trainee, Internship internship) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Next approveURL generated: ").append(approveUrl)
                .append(" /n").append(" for trainee with email: ").append(trainee.getEmail())
                .append(" /n").append(" for internship with id: ").append(internship.getId());
        logger.info(stringBuffer.toString());
    }
}


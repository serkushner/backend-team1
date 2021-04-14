package com.exadel.project.trainee.validator;

import com.exadel.project.common.exception.DoubleRegistrationException;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.service.AdditionalInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TraineeValidator {

    private final AdditionalInfoService additionalInfoService;

    public void checkDoubleRegistration(Trainee trainee, Internship internship){
        AdditionalInfo additionalInfo = additionalInfoService.getAdditionalInfoByInternshipAndTrainee(internship, trainee);
        if (additionalInfo != null){
            throw new DoubleRegistrationException();
        }
    }
}

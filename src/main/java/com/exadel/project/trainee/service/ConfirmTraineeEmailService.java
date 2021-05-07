package com.exadel.project.trainee.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.exception.TokenIsNotValidException;
import com.exadel.project.common.exception.TraineeAlreadyConfirmEmailException;
import com.exadel.project.configurations.JwtConfiguration;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.TraineeStatus;
import com.exadel.project.trainee.repository.AdditionalInfoRepository;
import com.exadel.project.trainee.repository.TraineeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmTraineeEmailService {

    private final JwtConfiguration jwtConfiguration;
    private final AdditionalInfoRepository additionalInfoRepository;

    public Boolean confirmTraineeEmail(String token) {
        if (token != null && jwtConfiguration.validateToken(token)) {
            AdditionalInfo additionalInfo = additionalInfoRepository.findById(Long.parseLong(jwtConfiguration.getIdFromToken(token))).orElseThrow(EntityNotFoundException::new);
            if (additionalInfo.getTraineeStatus() == TraineeStatus.EMAIL_NOT_CONFIRM) {
                additionalInfo.setTraineeStatus(TraineeStatus.REGISTERED);
                additionalInfoRepository.save(additionalInfo);
                return true;
            }
            throw new TraineeAlreadyConfirmEmailException();
        }
        throw new TokenIsNotValidException();
    }

}

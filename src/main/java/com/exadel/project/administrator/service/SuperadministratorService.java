package com.exadel.project.administrator.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.repository.AdditionalInfoRepository;
import com.exadel.project.trainee.service.TraineeStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuperadministratorService {

    private final AdditionalInfoRepository additionalInfoRepository;
    private final TraineeStatusService traineeStatusService;

    public void approveTraineeStatusBySuperadmin(Long id, Boolean isApproved) {
        AdditionalInfo additionalInfo = additionalInfoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (!isApproved) {
            traineeStatusService.changeTraineeStatusToRejected(additionalInfo);
        } else {
            traineeStatusService.changeTraineeStatus(additionalInfo);
        }
    }
}

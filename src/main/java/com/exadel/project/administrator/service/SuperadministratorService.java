package com.exadel.project.administrator.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.trainee.dto.TraineeToAdminDTO;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.TraineeStatus;
import com.exadel.project.trainee.mapper.AdditionalInfoMapper;
import com.exadel.project.trainee.repository.AdditionalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuperadministratorService {

    private static final Logger logger = LoggerFactory.getLogger(SuperadministratorService.class);

    private final AdditionalInfoRepository additionalInfoRepository;
    private final AdditionalInfoMapper additionalInfoMapper;

    public TraineeToAdminDTO changeTraineeStatus(Long id, Boolean isApproved) {
        AdditionalInfo additionalInfo = additionalInfoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        additionalInfo.setTraineeStatus(TraineeStatus.RECRUITER_INTERVIEW_REJECTED);
        if (!isApproved) {
            additionalInfo.setTraineeStatus(TraineeStatus.REJECTED);
        } else {
            TraineeStatus status = additionalInfo.getTraineeStatus();
            if (status == TraineeStatus.RECRUITER_INTERVIEW_ACCEPTED || status == TraineeStatus.RECRUITER_INTERVIEW_REJECTED) {
                additionalInfo.setTraineeStatus(TraineeStatus.RECRUITER_INTERVIEW_PASSED);
            } else if (status == TraineeStatus.TECHNICAL_INTERVIEW_ACCEPTED || status == TraineeStatus.TECHNICAL_INTERVIEW_REJECTED) {
                additionalInfo.setTraineeStatus(TraineeStatus.ACCEPTED);
            } else {
                logger.info("Trainee status is not available for changes");
            }
        }
        additionalInfoRepository.save(additionalInfo);
        return additionalInfoMapper.entityToDto(additionalInfo);
    }
}
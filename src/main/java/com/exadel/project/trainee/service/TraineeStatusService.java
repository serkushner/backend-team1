package com.exadel.project.trainee.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.exception.TraineeStatusIsNotAvailableForChangesException;
import com.exadel.project.trainee.dto.TraineeToAdminDTO;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.TraineeStatus;
import com.exadel.project.trainee.mapper.AdditionalInfoMapper;
import com.exadel.project.trainee.repository.AdditionalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraineeStatusService {

    private final AdditionalInfoRepository additionalInfoRepository;

    public void changeTraineeStatusToRejected(AdditionalInfo additionalInfo) {
        additionalInfo.setTraineeStatus(TraineeStatus.REJECTED);
        additionalInfoRepository.save(additionalInfo);
    }

    public void changeTraineeStatus(AdditionalInfo additionalInfo) {
        TraineeStatus status = additionalInfo.getTraineeStatus();
        switch (status) {
            case EMAIL_NOT_CONFIRM:
                additionalInfo.setTraineeStatus(TraineeStatus.REGISTERED);
                break;
            case REGISTERED:
                additionalInfo.setTraineeStatus(TraineeStatus.RECRUITER_INTERVIEW_PENDING);
                break;
            case RECRUITER_INTERVIEW_ACCEPTED:
            case RECRUITER_INTERVIEW_REJECTED:
                additionalInfo.setTraineeStatus(TraineeStatus.RECRUITER_INTERVIEW_PASSED);
                break;
            case RECRUITER_INTERVIEW_PASSED:
                additionalInfo.setTraineeStatus(TraineeStatus.TECHNICAL_INTERVIEW_PENDING);
                break;
            case TECHNICAL_INTERVIEW_ACCEPTED:
            case TECHNICAL_INTERVIEW_REJECTED:
                additionalInfo.setTraineeStatus(TraineeStatus.ACCEPTED);
                break;
            default: throw new TraineeStatusIsNotAvailableForChangesException();
        }
        additionalInfoRepository.save(additionalInfo);
    }

    public void changeTraineeStatusAfterInterview(Long id, boolean isApproved) {
        AdditionalInfo additionalInfo = additionalInfoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        TraineeStatus status = additionalInfo.getTraineeStatus();
        switch (status) {
            case RECRUITER_INTERVIEW_PENDING:
                if (isApproved) {
                    additionalInfo.setTraineeStatus(TraineeStatus.RECRUITER_INTERVIEW_ACCEPTED);
                } else {
                    additionalInfo.setTraineeStatus(TraineeStatus.RECRUITER_INTERVIEW_REJECTED);
                }
                break;
            case TECHNICAL_INTERVIEW_PENDING:
                if (isApproved) {
                    additionalInfo.setTraineeStatus(TraineeStatus.TECHNICAL_INTERVIEW_ACCEPTED);
                } else {
                    additionalInfo.setTraineeStatus(TraineeStatus.TECHNICAL_INTERVIEW_REJECTED);
                }
                break;
            default:
                throw new TraineeStatusIsNotAvailableForChangesException();
        }
        additionalInfoRepository.save(additionalInfo);
    }
}

package com.exadel.project.administrator.service;

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
public class SuperadministratorService {

    private final AdditionalInfoRepository additionalInfoRepository;
    private final AdditionalInfoMapper additionalInfoMapper;

    public TraineeToAdminDTO changeTraineeStatus(Long id, Boolean isApproved) {
        AdditionalInfo additionalInfo = additionalInfoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        TraineeStatus traineeStatus = TraineeStatus.getNextStatus(additionalInfo.getTraineeStatus(), isApproved);
        additionalInfo.setTraineeStatus(traineeStatus);
        additionalInfoRepository.save(additionalInfo);
        return additionalInfoMapper.entityToDto(additionalInfo);
    }
}

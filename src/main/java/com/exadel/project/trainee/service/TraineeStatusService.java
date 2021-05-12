package com.exadel.project.trainee.service;

import com.exadel.project.common.exception.EntityNotFoundException;
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
    private final AdditionalInfoMapper additionalInfoMapper;

    public TraineeToAdminDTO changeTraineeStatusToRejected(Long id) {
        AdditionalInfo additionalInfo = additionalInfoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        additionalInfo.setTraineeStatus(TraineeStatus.REJECTED);
        additionalInfoRepository.save(additionalInfo);
        return additionalInfoMapper.entityToDto(additionalInfo);
    }

    public TraineeToAdminDTO changeTraineeStatus(Long id) {
        AdditionalInfo additionalInfo = additionalInfoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        TraineeStatus status = additionalInfo.getTraineeStatus();
        switch (status) {
            case
        }




        additionalInfo.setTraineeStatus(TraineeStatus.REJECTED);
        additionalInfoRepository.save(additionalInfo);
        return additionalInfoMapper.entityToDto(additionalInfo);
    }
}

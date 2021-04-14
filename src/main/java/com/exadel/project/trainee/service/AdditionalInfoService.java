package com.exadel.project.trainee.service;

import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.mapper.AdditionalInfoMapper;
import com.exadel.project.trainee.repository.AdditionalInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdditionalInfoService extends BaseService<AdditionalInfo, AdditionalInfoRepository> {

    private final AdditionalInfoRepository additionalInfoRepository;
    private final AdditionalInfoMapper additionalInfoMapper;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public AdditionalInfo getAdditionalInfoByInternshipAndTrainee(Internship internship, Trainee trainee){
        return additionalInfoRepository.findAdditionalInfoByInternshipAndTrainee(internship, trainee);
    }

    public AdditionalInfo saveAdditionalInfo(TraineeDTO traineeDTO, Trainee trainee, Internship internship){
        AdditionalInfo additionalInfo = additionalInfoMapper.dtoToEntity(traineeDTO, trainee, internship);
        return additionalInfoRepository.save(additionalInfo);
    }
}
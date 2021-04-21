package com.exadel.project.trainee.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.dto.TraineeHistoryDTO;
import com.exadel.project.trainee.dto.TraineeToAdminDTO;
import com.exadel.project.trainee.dto.TraineeToAdminDetailsDTO;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.mapper.AdditionalInfoMapper;
import com.exadel.project.trainee.repository.AdditionalInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<TraineeToAdminDTO> findByNotFinishedInternships(String search, String sortFields){
        return additionalInfoRepository.findAllByInternship_EndDateAfter(LocalDate.now()).stream()
                .map(additionalInfoMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public TraineeToAdminDetailsDTO getAdditionalInfoById(Long id) throws EntityNotFoundException {
        AdditionalInfo additionalInfo = super.getEntityById(id);
        return additionalInfoMapper.entityToDto(additionalInfo, null);
    }

    public List<TraineeHistoryDTO> getTraineeHistory(Long id, String search, String sortFields){
        return additionalInfoRepository.findAllByTraineeId(id).stream()
                .map(additionalInfo -> additionalInfoMapper.entityToTraineeHistoryDTO(additionalInfo, null))
                .collect(Collectors.toList());
    }
}
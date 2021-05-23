package com.exadel.project.trainee.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.mailSender.EmailServiceImpl;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.interview.dto.InterviewDTO;
import com.exadel.project.interview.service.InterviewService;
import com.exadel.project.trainee.dto.*;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.mapper.AdditionalInfoMapper;
import com.exadel.project.trainee.repository.AdditionalInfoRepository;
import com.exadel.project.trainee.service.rsql.AdditionalInfoRsqlSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdditionalInfoService extends BaseService<AdditionalInfo, AdditionalInfoRepository> {
    {
        defaultSortingField = "internship.title";
    }
    private final AdditionalInfoRepository additionalInfoRepository;
    private final AdditionalInfoMapper additionalInfoMapper;
    private final AdditionalInfoRsqlSpecification additionalInfoRsqlSpecification;
    private final InterviewService interviewService;
    private final EmailServiceImpl emailService;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        return additionalInfoRsqlSpecification;
    }

    public AdditionalInfo getAdditionalInfoByInternshipAndTrainee(Internship internship, Trainee trainee){
        return additionalInfoRepository.findAdditionalInfoByInternshipAndTrainee(internship, trainee);
    }

    public AdditionalInfo saveAdditionalInfo(TraineeDTO traineeDTO, Trainee trainee, Internship internship){
        AdditionalInfo additionalInfo = additionalInfoMapper.dtoToEntity(traineeDTO, trainee, internship);
        return additionalInfoRepository.save(additionalInfo);
    }

    public List<TraineeToAdminDTO> findByNotFinishedInternships(String search, String sortFields){
        String endDateToday = "internship.endDate>" + LocalDate.now();
        search = search == null ? endDateToday : search + ";" + endDateToday;
        Sort sort = getSort(sortFields);
        return super.findBySpecifications(search, sort).stream()
                .map(additionalInfoMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public TraineeToAdminDetailsDTO getAdditionalInfoById(Long id) throws EntityNotFoundException {
        AdditionalInfo additionalInfo = super.getEntityById(id);
        List<InterviewDTO> interviews = interviewService.getAllByTraineeAndInternshipId(additionalInfo.getTrainee().getId(), additionalInfo.getInternship().getId());
        return additionalInfoMapper.entityToDto(additionalInfo, interviews);
    }

    public List<TraineeHistoryDTO> getTraineeHistory(Long id, String search, String sortFields){
        return additionalInfoRepository.findAllByTraineeId(id).stream()
                .map(additionalInfoMapper::entityToTraineeHistoryDTO)
                .collect(Collectors.toList());
    }

    public void deleteAdditionalInfo(Long id){
        additionalInfoRepository.deleteById(id);
    }

    public NotifyTraineesDTO notifyTrainees(NotifyTraineesDTO dto){
        List<AdditionalInfo> additionalInfoList = additionalInfoRepository.findAllByIdIn(dto.getAdditionalInfoIds());
        additionalInfoList.stream()
                .map(AdditionalInfo::getTrainee)
                .map(Trainee::getEmail)
                .forEach(email->emailService.sendSimpleMessage(email, "Exadel internship", dto.getMessage()));
        return dto;
    }
}
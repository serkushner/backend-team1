package com.exadel.project.trainee.service;

import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.internship.entity.Country;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.service.CountryService;
import com.exadel.project.internship.service.InternshipService;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.InterviewPeriod;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.mapper.AdditionalInfoMapper;
import com.exadel.project.trainee.mapper.TraineeMapper;
import com.exadel.project.trainee.repository.AdditionalInfoRepository;
import com.exadel.project.trainee.repository.TraineeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraineeService extends BaseService<Trainee, TraineeRepository> {
    private static final Long TRAINEE_STATUS_FIRST_ID = 1L;

    private final TraineeMapper traineeMapper;
    private final CountryService countryService;
    private final TraineeStatusService traineeStatusService;
    private final TraineeRepository traineeRepository;
    private final AdditionalInfoMapper additionalInfoMapper;
    private final InternshipService internshipService;
    private final AdditionalInfoRepository additionalInfoRepository;
    private final InterviewPeriodService interviewPeriodService;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public TraineeDTO addTrainee(TraineeDTO traineeDTO, Long internshipId){
        Trainee trainee = traineeMapper.dtoToEntity(traineeDTO);
        Country country = countryService.getByName(traineeDTO.getLocation());
        trainee.setCountry(country);
//        trainee.setStatus(traineeStatusService.getEntityById(TRAINEE_STATUS_FIRST_ID));
        trainee = traineeRepository.save(trainee);
        Internship internship = internshipService.getEntityById(internshipId);
        AdditionalInfo additionalInfo = additionalInfoMapper.dtoToEntity(traineeDTO, trainee, internship);
        additionalInfoRepository.save(additionalInfo);
        InterviewPeriod interviewPeriod = null;
        if (traineeDTO.getDay1() != null && traineeDTO.getHours1() != null){
            interviewPeriod = interviewPeriodService.addInterviewPeriod(traineeDTO.getHours1(), traineeDTO.getDay1());
            trainee.getInterviewPeriods().add(interviewPeriod);
            interviewPeriod.getTrainees().add(trainee);
        }
        if (traineeDTO.getDay2() != null && traineeDTO.getHours2() != null){
            interviewPeriod = interviewPeriodService.addInterviewPeriod(traineeDTO.getHours2(), traineeDTO.getDay2());
            trainee.getInterviewPeriods().add(interviewPeriod);
            interviewPeriod.getTrainees().add(trainee);
        }
        if (traineeDTO.getDay3() != null && traineeDTO.getHours3() != null){
            interviewPeriod = interviewPeriodService.addInterviewPeriod(traineeDTO.getHours3(), traineeDTO.getDay3());
            trainee.getInterviewPeriods().add(interviewPeriod);
            interviewPeriod.getTrainees().add(trainee);
        }
        return traineeDTO;
    }
}

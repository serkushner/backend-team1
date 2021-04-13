package com.exadel.project.trainee.service;

import com.exadel.project.common.exception.DoubleRegistrationException;
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
import com.exadel.project.trainee.mapper.TraineeMapper;
import com.exadel.project.trainee.repository.TraineeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TraineeService extends BaseService<Trainee, TraineeRepository> {

    private final TraineeMapper traineeMapper;
    private final CountryService countryService;
    private final TraineeRepository traineeRepository;
    private final InternshipService internshipService;
    private final AdditionalInfoService additionalInfoService;
    private final InterviewPeriodService interviewPeriodService;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public TraineeDTO addTrainee(TraineeDTO traineeDTO, Long internshipId){
        Trainee trainee = traineeRepository.findTraineeByEmail(traineeDTO.getEmail());
        Internship internship = internshipService.getEntityById(internshipId);
        checkDoubleRegistration(trainee, internship);
        if (trainee != null){
            traineeMapper.updateTrainee(traineeDTO, trainee);
        }else {
            trainee = traineeMapper.dtoToEntity(traineeDTO);
        }
        if (traineeDTO.getLocation() != null){
            addCountryToTrainee(traineeDTO.getLocation(), trainee);
        }
        trainee = traineeRepository.save(trainee);
        if (traineeDTO.getDates() != null){
            addInterviewPeriodsToTrainee(traineeDTO.getDates().values(), trainee);
        }
        additionalInfoService.saveAdditionalInfo(traineeDTO, trainee, internship);
        return traineeMapper.entityToDto(trainee);
    }

    private void addCountryToTrainee(String location, Trainee trainee){
        Country country = countryService.getByName(location);
        trainee.setCountry(country);
    }

    private void addInterviewPeriodsToTrainee(Collection<Map<String, String>> dates, Trainee trainee){
        List<InterviewPeriod> interviewPeriods = interviewPeriodService.addInterviewPeriod(dates, trainee);
        trainee.getInterviewPeriods().addAll(interviewPeriods);
    }

    private void checkDoubleRegistration(Trainee trainee, Internship internship){
        AdditionalInfo additionalInfo = additionalInfoService.getAdditionalInfoByInternshipAndTrainee(internship, trainee);
        if (additionalInfo != null){
            throw new DoubleRegistrationException();
        }
    }
}
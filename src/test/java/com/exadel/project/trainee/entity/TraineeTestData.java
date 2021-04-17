package com.exadel.project.trainee.entity;

import com.exadel.project.internship.entity.Country;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.trainee.dto.TraineeDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TraineeTestData {

    public TraineeDTO getTestTraineeDto(){
        TraineeDTO traineeDTO = new TraineeDTO();
        traineeDTO.setId(1L);
        traineeDTO.setName("Kastus");
        traineeDTO.setSurname("Kalinouski");
        traineeDTO.setEmail("kalinouski@gmail.com");
        traineeDTO.setPhone("375-29-888-18-63");
        traineeDTO.setLocation("Belarus");
        traineeDTO.setTraineeStatus("REGISTERED");
        traineeDTO.setSkype("skype.com");
        traineeDTO.setRecipient(true);
        traineeDTO.setEnglish("C1");
        traineeDTO.setCv("CV link");
        traineeDTO.setGithub("GitHub link");
        traineeDTO.setDates(getDates());
        return traineeDTO;
    }

    private List<Map<String, String>> getDates(){
        List<Map<String, String>> dates = new ArrayList<>();
        Map<String, String> date = new HashMap<>();
        date.put("day", "MONDAY");
        date.put("time", "10.00 - 13.00");
        dates.add(date);
        return dates;
    }

    public Country getTestCountry(){
        Country country = new Country();
        country.setId(1L);
        country.setName("Belarus");
        country.setCode(911);
        return country;
    }

    public Trainee getTestTrainee(){
        Trainee trainee = new Trainee();
        trainee.setId(1L);
        trainee.setName("Kastus");
        trainee.setSurname("Kalinouski");
        trainee.setEmail("kalinouski@gmail.com");
        trainee.setPhone("375-29-888-18-63");
        trainee.setCountry(getTestCountry());
        trainee.setSkype("skype.com");
        trainee.setRecipient(true);
        return trainee;
    }

    public InterviewPeriod getTestInterviewPeriod(){
        InterviewPeriod interviewPeriod = new InterviewPeriod();
        interviewPeriod.setId(1L);
        interviewPeriod.setStartTime(LocalTime.of(10, 0));
        interviewPeriod.setEndTime(LocalTime.of(13, 0));
        interviewPeriod.setDayOfWeek(DayOfWeek.MONDAY);
        return interviewPeriod;
    }

    public AdditionalInfo getTestAdditionalInfo(){
        AdditionalInfo additionalInfo = new AdditionalInfo();
        additionalInfo.setId(1L);
        additionalInfo.setEnglish("C1");
        additionalInfo.setCv("CV link");
        additionalInfo.setGithub("GitHub link");
        additionalInfo.setInternship(getTestInternship());
        additionalInfo.setTraineeStatus(TraineeStatus.REGISTERED);
        return additionalInfo;
    }

    public Internship getTestInternship(){
        Internship internship = new Internship();
        internship.setId(1L);
        return internship;
    }
}

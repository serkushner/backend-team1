package com.exadel.project.trainee.entity;

import com.exadel.project.country.entity.Country;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.trainee.dto.TraineeDTO;
import com.exadel.project.trainee.dto.TraineeToAdminDTO;
import com.exadel.project.trainee.dto.TraineeToAdminDetailsDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

@Component
public class TraineeTestData {

    public TraineeDTO getResponseTestTraineeDto(){
        TraineeDTO traineeDTO = getRequestTestTraineeDto();
        traineeDTO.setId(1L);
        traineeDTO.setTraineeStatus("REGISTERED");
        return traineeDTO;
    }

    public TraineeDTO getRequestTestTraineeDto(){
        TraineeDTO traineeDTO = new TraineeDTO();
        traineeDTO.setName("Kastus");
        traineeDTO.setSurname("Kalinouski");
        traineeDTO.setEmail("kalinouski@gmail.com");
        traineeDTO.setPhone("375-29-888-18-63");
        traineeDTO.setLocation("Belarus");
        traineeDTO.setSkype("skype.com");
        traineeDTO.setRecipient(true);
        traineeDTO.setEnglish("C1");
        traineeDTO.setCv("CV link");
        traineeDTO.setGithub("GitHub link");
        traineeDTO.setDates(getDates());
        return traineeDTO;
    }

    public TraineeDTO getTestUpdateTraineeDTO(){
        TraineeDTO traineeDTO = getResponseTestTraineeDto();
        traineeDTO.setId(1L);
        traineeDTO.setSkype("new skype");
        traineeDTO.setEmail("new email");
        traineeDTO.setTraineeStatus("REGISTERED");
        traineeDTO.setEnglish("C1");
        traineeDTO.setCv("CV link");
        traineeDTO.setGithub("GitHub link");
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

    public Trainee getResponseTestTrainee(){
        Trainee trainee = getRequestTestTrainee();
        trainee.setId(1L);
        return trainee;
    }

    public Trainee getRequestTestTrainee(){
        Trainee trainee = new Trainee();
        trainee.setName("Kastus");
        trainee.setSurname("Kalinouski");
        trainee.setEmail("kalinouski@gmail.com");
        trainee.setPhone("375-29-888-18-63");
        trainee.setCountry(getTestCountry());
        trainee.setSkype("skype.com");
        trainee.setRecipient(true);
        return trainee;
    }

    public Trainee getTestUpdateTrainee(){
        Trainee trainee = getResponseTestTrainee();
        trainee.setSkype("new skype");
        trainee.setEmail("new email");
        return trainee;
    }

    public InterviewPeriod getResponseTestInterviewPeriod(){
        InterviewPeriod interviewPeriod = getRequestInterviewPeriod();
        interviewPeriod.setId(1L);
        return interviewPeriod;
    }

    public InterviewPeriod getRequestInterviewPeriod(){
        InterviewPeriod interviewPeriod = new InterviewPeriod();
        interviewPeriod.setDayOfWeek(DayOfWeek.MONDAY);
        interviewPeriod.setStartTime(LocalTime.of(10, 0));
        interviewPeriod.setEndTime(LocalTime.of(13, 0));
        return interviewPeriod;
    }

    public AdditionalInfo getTestAdditionalInfo(){
        AdditionalInfo additionalInfo = new AdditionalInfo();
        additionalInfo.setId(1L);
        additionalInfo.setEnglish(EnglishLevel.C1);
        additionalInfo.setCv("CV link");
        additionalInfo.setGithub("GitHub link");
        additionalInfo.setInternship(getTestInternship());
        additionalInfo.setTraineeStatus(TraineeStatus.REGISTERED);
        additionalInfo.setTrainee(getResponseTestTrainee());
        return additionalInfo;
    }

    public Internship getTestInternship(){
        Internship internship = new Internship();
        internship.setId(1L);
        return internship;
    }

    public TraineeToAdminDTO getTestTraineeToAdminDTO(){
        TraineeToAdminDTO traineeToAdminDTO = new TraineeToAdminDTO();
        traineeToAdminDTO.setTraineeStatus("REGISTERED");
        traineeToAdminDTO.setTraineeName("Kastus");
        traineeToAdminDTO.setEmail("kalinouski@gmail.com");
        traineeToAdminDTO.setAdditionalInfoId(1L);
        traineeToAdminDTO.setTraineeLocation("Belarus");
        traineeToAdminDTO.setTraineeSurname("Kalinouski");
        traineeToAdminDTO.setSubjects(Collections.emptyList());
        return traineeToAdminDTO;
    }

    public TraineeToAdminDetailsDTO getTestTraineeToAdminDetailsDTO(){
        TraineeToAdminDetailsDTO traineeToAdminDetailsDTO = new TraineeToAdminDetailsDTO();
        traineeToAdminDetailsDTO.setId(1L);
        traineeToAdminDetailsDTO.setTraineeId(1L);
        traineeToAdminDetailsDTO.setDates(Collections.emptyList());
        traineeToAdminDetailsDTO.setEnglish("C1");
        traineeToAdminDetailsDTO.setCv("CV link");
        traineeToAdminDetailsDTO.setGithub("GitHub link");
        traineeToAdminDetailsDTO.setInternshipId(1L);
        traineeToAdminDetailsDTO.setTraineeStatus("REGISTERED");
        traineeToAdminDetailsDTO.setEmail("kalinouski@gmail.com");
        traineeToAdminDetailsDTO.setLocation("Belarus");
        traineeToAdminDetailsDTO.setName("Kastus");
        traineeToAdminDetailsDTO.setPhone("375-29-888-18-63");
        traineeToAdminDetailsDTO.setRecipient(true);
        traineeToAdminDetailsDTO.setSkype("skype.com");
        traineeToAdminDetailsDTO.setSurname("Kalinouski");
        return traineeToAdminDetailsDTO;
    }
}

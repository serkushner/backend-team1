package com.exadel.project.trainee.service;

import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.trainee.entity.InterviewPeriod;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.repository.InterviewPeriodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
public class InterviewPeriodService extends BaseService<InterviewPeriod, InterviewPeriodRepository> {
    private final InterviewPeriodRepository interviewPeriodRepository;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public List<InterviewPeriod> addInterviewPeriod(List<Map<String, String>> dates, Trainee trainee){
        List<InterviewPeriod> interviewPeriods = new ArrayList<>();
        for (Map<String, String> date : dates) {
            DayOfWeek dayOfWeek = DayOfWeek.valueOf(date.get("day").toUpperCase());
            LocalTime startTime = getTimeFromPeriod(date.get("time"), 1);
            LocalTime endTime = getTimeFromPeriod(date.get("time"), 3);
            InterviewPeriod interviewPeriod = interviewPeriodRepository.findByDayOfWeekAndAndStartTimeAndEndTime(dayOfWeek, startTime, endTime);
            if (interviewPeriod == null){
                interviewPeriod = new InterviewPeriod();
                interviewPeriod.setDayOfWeek(dayOfWeek);
                interviewPeriod.setStartTime(startTime);
                interviewPeriod.setEndTime(endTime);
                interviewPeriod = interviewPeriodRepository.save(interviewPeriod);
            }
            if (!interviewPeriod.getTrainees().contains(trainee)){
                interviewPeriod.getTrainees().add(trainee);
            }
            interviewPeriods.add(interviewPeriod);
        }
        return interviewPeriods;
    }

    public List<InterviewPeriod> getInterviewPeriodsByTrainee(Trainee trainee){
        return interviewPeriodRepository.findAllByTrainees(trainee);
    }

    private LocalTime getTimeFromPeriod(String period, int startGroup){
        Pattern pattern = Pattern.compile("(\\d{2})\\.(\\d{2}) - (\\d{2})\\.(\\d{2})");
        Matcher matcher = pattern.matcher(period);
        matcher.find();
        int hours = Integer.parseInt(matcher.group(startGroup));
        int minutes = Integer.parseInt(matcher.group(startGroup + 1));
        return LocalTime.of(hours, minutes);
    }
}
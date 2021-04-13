package com.exadel.project.trainee.service;

import com.exadel.project.common.exception.NoSuchDayOfWeekException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.trainee.entity.DayOfWeek;
import com.exadel.project.trainee.entity.InterviewPeriod;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.repository.InterviewPeriodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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

    public List<InterviewPeriod> addInterviewPeriod(Collection<Map<String, String>> dates, Trainee trainee){
        List<InterviewPeriod> interviewPeriods = new ArrayList<>();
        dates.forEach(map->{
            InterviewPeriod interviewPeriod = new InterviewPeriod();
            interviewPeriod.setDayOfWeek(getDayOfWeek(map.get("day")));
            interviewPeriod.setStartTime(getTimeFromPeriod(map.get("time"), 1));
            interviewPeriod.setEndTime(getTimeFromPeriod(map.get("time"), 3));
            interviewPeriod = interviewPeriodRepository.save(interviewPeriod);
            interviewPeriod.getTrainees().add(trainee);
            interviewPeriods.add(interviewPeriod);
        });
        return interviewPeriods;
    }

    public List<InterviewPeriod> getInterviewPeriodsByTraineeInternship(Trainee trainee){
        return interviewPeriodRepository.findAllByTrainees(trainee);
    }

    private DayOfWeek getDayOfWeek(String day){
        switch (day){
            case "Monday": return DayOfWeek.MON;
            case "Tuesday": return DayOfWeek.TUE;
            case "Wednesday": return DayOfWeek.WED;
            case "Thursday": return DayOfWeek.THU;
            case "Friday": return DayOfWeek.FRI;
            case "Saturday": return DayOfWeek.SAT;
            case "Sunday": return DayOfWeek.SUN;
            default: throw new NoSuchDayOfWeekException();
        }
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
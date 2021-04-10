package com.exadel.project.trainee.service;

import com.exadel.project.common.exception.NoSuchDayOfWeekException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.trainee.entity.DayOfWeek;
import com.exadel.project.trainee.entity.InterviewPeriod;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.repository.InterviewPeriodRepository;
import com.exadel.project.trainee.repository.TraineeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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

    public InterviewPeriod addInterviewPeriod(String time, String day){
        InterviewPeriod interviewPeriod = new InterviewPeriod();
        interviewPeriod.setDayOfWeek(getDayOfWeek(day));
        List<LocalTime> localTimes = getTimeFromStrings(time);
        interviewPeriod.setStartTime(localTimes.get(0));
        interviewPeriod.setEndTime(localTimes.get(1));
        return interviewPeriodRepository.save(interviewPeriod);
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

    private List<String> getAllMatches(String times) {
        String regex = "(\\d?\\d.\\d\\d)";
        List<String> matches = new ArrayList<String>();
        Matcher m = Pattern.compile(regex).matcher(times);
        while(m.find()) {
            matches.add(m.group(1));
        }
        return matches;
    }

    private List<LocalTime> getTimeFromStrings(String times) {
        List<String> stringWithTime = getAllMatches(times);

        String regExHours = "(\\d?\\d).";
        Pattern hoursPattern = Pattern.compile(regExHours);
        String regExMinutes = ".(\\d\\d)";
        Pattern minutesPattern = Pattern.compile(regExMinutes);
        List<LocalTime> localTimes = new ArrayList<>();
        String hours;
        String minutes;
        LocalTime time;
        for (String s : stringWithTime) {
            Matcher matcher = hoursPattern.matcher(s);
            matcher.find();
            hours = matcher.group(1);
            matcher = minutesPattern.matcher(s);
            matcher.find();
            minutes = matcher.group(1);
            time = LocalTime.of(Integer.parseInt(hours), Integer.parseInt(minutes));
            localTimes.add(time);
        }
        return localTimes;
    }
}

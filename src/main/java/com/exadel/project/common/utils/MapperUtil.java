package com.exadel.project.common.utils;

import com.exadel.project.trainee.entity.InterviewPeriod;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapperUtil {
    public static <T> List<String> getStrings(List<T> sourceList, Function<T, String> function) {
        return Optional.ofNullable(sourceList)
                .stream()
                .flatMap(Collection::stream)
                .map(function)
                .collect(Collectors.toList());
    }

    public static List<Map<String, String>> getMapDates(List<InterviewPeriod> interviewPeriods) {
        List<Map<String, String>> dates = new ArrayList<>();
        for (InterviewPeriod interviewPeriod : interviewPeriods) {
            Map<String, String> dayOfWeekToTime = new HashMap<>();
            dayOfWeekToTime.put("day", interviewPeriod.getDayOfWeek().toString());
            String startTime = interviewPeriod.getStartTime().toString();
            String endTime = interviewPeriod.getEndTime().toString();
            dayOfWeekToTime.put("time", String.join(" - ", startTime, endTime));
            dates.add(dayOfWeekToTime);
        }
        return dates;
    }
}

package com.exadel.project.trainee.repository;

import com.exadel.project.trainee.entity.InterviewPeriod;
import com.exadel.project.trainee.entity.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface InterviewPeriodRepository extends JpaRepository<InterviewPeriod, Long>, JpaSpecificationExecutor<InterviewPeriod> {
    List<InterviewPeriod> findAllByTrainees(Trainee trainee);
    InterviewPeriod findByDayOfWeekAndAndStartTimeAndEndTime(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime);
}

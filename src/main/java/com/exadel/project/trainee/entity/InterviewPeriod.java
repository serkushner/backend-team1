package com.exadel.project.trainee.entity;

import com.exadel.project.administrator.entity.Role;
import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "interview_period")
public class InterviewPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @ManyToMany(mappedBy = "interviewPeriods")
    private List<Trainee> trainees = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", columnDefinition="ENUM('MON','TUE','WED','THU','FRI','SAT','SUN')")
    private DayOfWeek dayOfWeek;
}

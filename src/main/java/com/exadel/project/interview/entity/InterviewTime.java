package com.exadel.project.interview.entity;

import com.exadel.project.interviewer.entity.Interviewer;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "interview_time")
public class InterviewTime {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ManyToMany(mappedBy = "interviewTimes")
    private List<Interviewer> interviewers = new ArrayList<>();

    @Override
    public String toString() {
        return "InterviewTime{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}


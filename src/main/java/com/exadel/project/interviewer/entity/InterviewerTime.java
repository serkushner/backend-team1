package com.exadel.project.interviewer.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "interview_time")
public class InterviewerTime {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
//
//    @ManyToMany(mappedBy = "interviewTimes", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    private List<Interviewer> interviewers = new ArrayList<>();
}

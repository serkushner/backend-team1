package com.exadel.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "interview_time")
public class InterviewTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "interview_time__id")
    private Long id;

    @Column(name = "start")
    private Date start;

    @Column(name = "end")
    private Date end;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "interviewer_id")
    private Interviewer interviewer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = " interview_id")
    private Interview interview;
}

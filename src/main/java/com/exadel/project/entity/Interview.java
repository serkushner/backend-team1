package com.exadel.project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "interview")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "interview_id")
    private Long id;

    @Column(name = "additional_info")
    private String additionalInfo;

    @OneToOne(mappedBy = "interview", fetch = FetchType.LAZY)
    @JoinColumn(name = "time_id")
    private InterviewTime interviewTime;

    @OneToOne(mappedBy = "interview", fetch = FetchType.LAZY)
    @JoinColumn(name = "interviewer_id")
    private Interviewer interviewer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;

}

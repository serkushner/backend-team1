package com.exadel.project.interview.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "interview")
public class Interview {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Description of interview should not be blank")
    @Column(name = "additional_info")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "time_id", referencedColumnName = "id")
    private InterviewTime interviewTime;

    @ManyToOne
    @JoinColumn(name = "trainee_id", nullable = false)
    private Trainee trainee;

    @ManyToOne
    @JoinColumn(name = "interviewer_id", nullable = false)
    private Interviewer interviewer;
}



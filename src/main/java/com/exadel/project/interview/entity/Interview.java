package com.exadel.project.interview.entity;

import com.exadel.project.internship.entity.Internship;
import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.trainee.entity.Trainee;
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

    @ManyToOne
    @JoinColumn(name = "time_id", nullable = false)
    private InterviewTime interviewTime;

    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;

    @ManyToOne
    @JoinColumn(name = "internship_id")
    private Internship internship;

    @ManyToOne
    @JoinColumn(name = "interviewer_id", nullable = false)
    private Interviewer interviewer;
}
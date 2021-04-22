package com.exadel.project.trainee.entity;

import com.exadel.project.internship.entity.Internship;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "additional_info",
        uniqueConstraints = {@UniqueConstraint(columnNames =
                {"trainee_id", "internship_id"})})
public class AdditionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "english", columnDefinition = "ENUM('A1', 'A2', 'B1', 'B2', 'C1', 'C2')")
    private EnglishLevel english;

    @Column(name = "cv")
    private String cv;

    @Column(name = "github")
    private String github;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "internship_id")
    private Internship internship;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_trainee", columnDefinition = "ENUM('REGISTERED', 'RECRUITER_INTERVIEW_PENDING', 'RECRUITER_INTERVIEW_REJECTED', 'RECRUITER_INTERVIEW_ACCEPTED', 'RECRUITER_INTERVIEW_PASSED', 'TECHNICAL_INTERVIEW_PENDING', 'TECHNICAL_INTERVIEW_REJECTED', 'TECHNICAL_INTERVIEW_ACCEPTED', 'REJECTED', 'ACCEPTED')")
    private TraineeStatus traineeStatus;
}
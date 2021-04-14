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

    @Column(name = "additional_info")
    private String english;

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
    @Column(name = "status_trainee", columnDefinition="ENUM('REGISTERED', 'RECRUITER_INTERVIEW_PENDING', 'RECRUITER_INTERVIEW_PASSED', 'TECHNICAL_INTERVIEW_PENDING', 'TECHNICAL_INTERVIEW_PASSED', 'REJECTED', 'ACCEPTED', 'PENDING_FOR_DECISION')")
    private TraineeStatus traineeStatus;
}
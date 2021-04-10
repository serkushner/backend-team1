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
    private String additionalInfo;

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
}

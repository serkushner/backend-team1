package com.exadel.project.interviewer.entity;

import com.exadel.project.interview.entity.Interview;
import com.exadel.project.interview.entity.InterviewTime;
import com.exadel.project.subject.entity.Subject;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "interviewer")
public class Interviewer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Interviewer's name should not be empty")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Interviewer's surname should not be empty")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Interviewer's email should not be empty")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Interviewer's phone should not be empty")
    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "ENUM('HR','TECH')")
    private InterviewerType type;

    @Column(name = "skype")
    private String skype;

    @OneToMany(mappedBy = "interviewer")
    private List<Interview> interviews;

    @ManyToMany
    @JoinTable(name = "interviewer_subject",
            joinColumns = @JoinColumn(name = "interviewer_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "interviewer_time",
            joinColumns = @JoinColumn(name = "interviewer_id"),
            inverseJoinColumns = @JoinColumn(name = "time_id"))
    private List<InterviewTime> interviewTimes = new ArrayList<>();
}

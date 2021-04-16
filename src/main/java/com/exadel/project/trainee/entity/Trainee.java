package com.exadel.project.trainee.entity;

import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.internship.entity.Country;
import com.exadel.project.interview.entity.Interview;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "trainee")
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name of trainee should not be empty")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Surname of trainee should not be empty")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Email of trainee should not be empty")
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Phone number of trainee should not be empty")
    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "skype")
    private String skype;

    @ManyToOne
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;

    @NotNull
    @Column(name = "recipient")
    private Boolean recipient;

    @ManyToMany
    @JoinTable(name = "interview_period_trainee",
            joinColumns = @JoinColumn(name = "trainee_id"),
            inverseJoinColumns = @JoinColumn(name = "interview_period_id")
    )
    private List<InterviewPeriod> interviewPeriods = new ArrayList<>();

    @OneToMany(mappedBy = "trainee")
    private List<Interview> interviews;
}
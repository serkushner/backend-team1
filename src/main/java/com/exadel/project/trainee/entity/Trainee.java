package com.exadel.project.trainee.entity;

import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.internship.entity.Country;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Phone number of trainee should not be empty")
    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private TraineeStatus status;

    @Column(name = "linkedin")
    private String linkedInLink;

    @Column(name = "skype")
    private String skype;

    @OneToOne
    @JoinColumn()
    private Administrator administrator;

    @NotNull
    @Column(name = "recipient")
    private Boolean recipient;
}
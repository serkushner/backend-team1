package com.exadel.project.student.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "trainees")
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trainee_id")
    private Long id;

    @Size(min = 2,max = 30,message ="name should be from 2 to 30 symbols" )
    @Column(name = "name")
    private String name;

    @Size(min = 2,max = 30,message ="surname should be from 2 to 30 symbols" )
    @Column(name = "surname")
    private String surname;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "github")
    private String github;

    @Column(name = "linkedin")
    @NotNull
    private String linkedin;

    @Column(name = "skype")
    private String skype;

    @Column(name = "cv")
    private String cv;

    @Column(name = "administrator_id")
    private Long administratorId;
}

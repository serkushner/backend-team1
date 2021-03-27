package com.exadel.project.student.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "trainees")
@Data
@NoArgsConstructor
public class Trainees {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trainee_id")
    private Long id;

    @NotBlank
    @Size(min = 2,max = 30,message ="name should be from 2 to 30 symbols" )
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(min = 2,max = 30,message ="surname should be from 2 to 30 symbols" )
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Column(name = "email",unique = true)
    private String email;

    @NotBlank(message = "Phone should not be empty")
    @Column(name = "phone")
    private String phoneNumber;

    @NotNull
    @Column(name = "country_id")
    private Long countryId;

    @NotNull
    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "github")
    private String github;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "skype")
    private String skype;

    @Column(name = "cv")
    private String cv;

    @Column(name = "administrator_id")
    private Long administratorId;
}

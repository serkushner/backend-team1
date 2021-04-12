package com.exadel.project.interviewer.entity;

import com.exadel.project.interviewer.entity.InterviewerType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
}

package com.exadel.project.interviewer.entity;



import com.exadel.project.Interview.enity.Interview;
import com.exadel.project.interview_time.entity.InterviewTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "interviewer")
public class Interviewer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "interviewer_id")
    private Long id;

    @NotBlank
    @Size(min = 5, max = 30, message = "Name should be from 5 to 30 symbols")
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(min = 5, max = 30, message = "Name should be from 5 to 30 symbols")
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @Email
    @Size(min = 5, max = 30, message = "Email should be from 5 to 30 symbols")
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min = 7, max = 30, message = "Phone should be from 7 to 30 symbols")
    @Column(name = "phone")
    private String phone;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeInterviewer type;

    @OneToOne(mappedBy = "interviewer", fetch = FetchType.LAZY)
    @JoinColumn(name = "time_id")
    private InterviewTime interviewTime;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = " interview_id")
    private Interview interview;

}

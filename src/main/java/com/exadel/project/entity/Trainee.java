package com.exadel.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trainees")
@Data
@NoArgsConstructor
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trainee_id")
    private Long id;

    @NotBlank
    @Size(min = 2, max = 30, message = "name should be from 2 to 30 symbols")
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(min = 2, max = 30, message = "surname should be from 2 to 30 symbols")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Phone should not be empty")
    @Column(name = "phone")
    private String phoneNumber;

    @NotNull
    @Column(name = "country_id")
    private Long countryId;
//
//    @NotNull
//    @Column(name = "status_id")
//    private Long statusId;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "messenger_id")
    private Messenger messenger;

    @OneToMany(mappedBy = "trainee", cascade = CascadeType.ALL)
    private List<Interview> interviews = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id")
    private TraineeStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mark_id")
    private Mark mark;


    /*
    Будет пустая калонка, без дабовления ID Trainees, если так сеттер не определить
     */
    public void setInterview(List<Interview> interviews) {
        if (interviews != null) {
            interviews.forEach(a -> {
                a.setTrainee(this);
            });
        }
        this.interviews = interviews;
    }
}

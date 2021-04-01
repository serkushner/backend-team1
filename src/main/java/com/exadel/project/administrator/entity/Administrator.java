package com.exadel.project.administrator.entity;



import com.exadel.project.trainee.entity.Trainee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "administrator")
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_id")
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30, message = "Login should be from 3 to 30 symbols")
    @Column(name = "login")
    private String login;

    @NotBlank
    @Size(min = 3, max = 30, message = "Password should be from 3 to 30 symbols")
    @Column(name = "password")
    private String password;

    @NotBlank
    @Email
    @Size(min = 5, max = 30, message = "Email should be from 5 to 30 symbols")
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min = 7, max = 30, message = "Phone should be from 7 to 30 symbols")
    @Column(name = "phone")
    private String phone;

    @Size(max = 30, message = "Phone should be to 30 symbols")
    @Column(name = "skype")
    private String skype;

    @NotBlank
    @Size(min = 5, max = 30, message = "Name should be from 5 to 30 symbols")
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(min = 5, max = 30, message = "Surname should be from 5 to 30 symbols")
    @Column(name = "surname")
    private String surname;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleAdmin role;

    @OneToMany(mappedBy = "administrator", cascade = CascadeType.ALL)
    private List<Trainee> trainees = new ArrayList<>();


    /*
    Будет пустая калонка, без дабовления ID Trainees, если так сеттер не определить
     */
    public void setTrainees(List<Trainee> trainees) {
        if (trainees != null) {
           trainees.forEach(a -> {
                a.setAdministrator(this);
            });
        }
        this.trainees = trainees;
    }
}

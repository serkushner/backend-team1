package com.exadel.project.administrator.entity;

import com.exadel.project.trainee.entity.Trainee;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"password"})
@Entity
@Table(name = "administrator")
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Administrator login should not be empty")
    @Column(name = "login")
    private String login;

    @NotBlank(message = "Administrator email should not be empty")
    @Column(unique=true, name = "email")
    private String email;

    @NotBlank(message = "Administrator password should not be empty")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Administrator name should not be empty")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Administrator surname should not be empty")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Administrator phone should not be empty")
    @Column(name = "phone")
    private String phone;

    @Column(name = "skype")
    private String skype;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition="ENUM('ADMIN','SUPERADMIN')")
    private Role role;

    @OneToMany(mappedBy = "administrator")
    private List<Trainee> trainees = new ArrayList<>();

}

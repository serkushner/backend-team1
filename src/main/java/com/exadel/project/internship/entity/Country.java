package com.exadel.project.internship.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private int code;

    @NotBlank(message = "Title of country should not be empty")
    @Column(name = "name")
    private String name;
}

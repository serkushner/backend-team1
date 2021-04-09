package com.exadel.project.trainee.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "trainee")
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


}

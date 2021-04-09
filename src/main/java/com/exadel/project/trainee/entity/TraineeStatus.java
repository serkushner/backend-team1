package com.exadel.project.trainee.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "trainee_status")
public class TraineeStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Trainee status should not be empty")
    @Column(name = "name",
            unique = true)
    private String name;
}
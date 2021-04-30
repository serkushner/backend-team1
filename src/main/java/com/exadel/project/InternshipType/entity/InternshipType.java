package com.exadel.project.InternshipType.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "internship_type")
public class InternshipType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Type should not be empty")
    @Column(name = "type",nullable = false)
    private String type;
}

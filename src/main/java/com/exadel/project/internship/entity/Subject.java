package com.exadel.project.internship.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Title of subject should not be empty")
    @Column(name = "title")
    private String type;

    @ManyToMany(mappedBy = "subjects")
    private List<Internship> internships = new ArrayList<>();
}

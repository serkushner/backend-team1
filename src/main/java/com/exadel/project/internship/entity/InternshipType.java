package com.exadel.project.internship.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy="internship_type")
    private List<Internship> internships = new ArrayList<>();
}

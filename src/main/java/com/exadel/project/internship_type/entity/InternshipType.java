package com.exadel.project.internship_type.entity;

import com.exadel.project.internship.entity.Internship;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "internship_type")
@Setter
@Getter
public class InternshipType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy="internship_type")
    private List<Internship> comments = new ArrayList<>();
}

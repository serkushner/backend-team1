package com.exadel.project.skill.entity;

import com.exadel.project.internship.entity.Internship;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "skill_name")
    private String name;

    @ManyToMany(mappedBy = "skills")
    private List<Internship> internships = new ArrayList<>();
}

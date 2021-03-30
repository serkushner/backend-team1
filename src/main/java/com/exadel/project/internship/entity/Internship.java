package com.exadel.project.internship.entity;

import com.exadel.project.internship_type.entity.InternshipType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "internship")
@Setter
@Getter
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Title should not be empty")
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "start_internship")
    private java.sql.Date start_internship;

    @NotNull
    @Column(name = "end_internship")
    private java.sql.Date end_internship;

    @NotNull
    @Column(name = "start_request")
    private java.sql.Date start_request;

    @NotNull
    @Column(name = "end_request")
    private java.sql.Date end_request;

    @NotBlank(message = "Description should not be empty")
    @Column(name = "description")
    private String description;

    @NotBlank(message = "Additional info should not be empty")
    @Column(name = "additional_info")
    private String additional_info;

    @NotNull
    @Column(name = "image")
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(name = "format")
    private Format format;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private InternshipType internship_type;


}

package com.exadel.project.internship.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "internship")
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Title of internship should not be empty")
    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(name = "internship_subject",
            joinColumns = @JoinColumn(name = "internship_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects = new ArrayList<>();

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;


    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;


    @Column(name = "start_request_date")
    private LocalDate startRequestDate;


    @Column(name = "end_request_date")
    private LocalDate endRequestDate;

    @NotBlank(message = "Description of internship should not be empty")
    @Column(name = "description")
    private String description;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "image")
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(name = "format")
    private Format format;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private InternshipType internshipType;


}
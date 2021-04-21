package com.exadel.project.internship.repository;

import com.exadel.project.internship.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Long>, JpaSpecificationExecutor<Internship> {
    Internship findInternshipByTitleAndStartDate(String title, LocalDate startDate);
    Internship findByIdAndPublished(Long id, Boolean isPublished);
}

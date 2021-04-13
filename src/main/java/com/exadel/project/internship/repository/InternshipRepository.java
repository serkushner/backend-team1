package com.exadel.project.internship.repository;

import com.exadel.project.internship.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.Optional;

public interface InternshipRepository extends JpaRepository<Internship, Long>, JpaSpecificationExecutor<Internship> {
    Optional<Internship> findAllByNameAndStartDate(String name, LocalDate startDate);
}

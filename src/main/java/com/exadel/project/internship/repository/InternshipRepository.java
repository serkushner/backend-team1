package com.exadel.project.internship.repository;

import com.exadel.project.internship.entity.Internship;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InternshipRepository extends JpaRepository<Internship, Long>, JpaSpecificationExecutor<Internship> {
    List<Internship> findAllByStartDateIsAfter(LocalDate localDate, Sort sort);
    Optional<Internship> findByIdAndStartDateAfter(Long id, LocalDate localDate);
}

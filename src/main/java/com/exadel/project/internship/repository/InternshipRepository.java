package com.exadel.project.internship.repository;

import com.exadel.project.internship.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InternshipRepository extends JpaRepository<Internship, Long>, JpaSpecificationExecutor<Internship> {
}

package com.exadel.project.internship.repository;

import com.exadel.project.internship.entity.Country;
import com.exadel.project.internship.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubjectRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject> {
    Subject findByName(String name);
}

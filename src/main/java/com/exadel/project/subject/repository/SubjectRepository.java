package com.exadel.project.subject.repository;

import com.exadel.project.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface SubjectRepository extends
        JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject> {
    Optional<Subject> findSubjectByName(String name);
}

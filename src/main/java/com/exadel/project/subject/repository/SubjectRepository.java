package com.exadel.project.subject.repository;

import com.exadel.project.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubjectRepository extends
        JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject> {
    Subject findSubjectByName(String name);
}

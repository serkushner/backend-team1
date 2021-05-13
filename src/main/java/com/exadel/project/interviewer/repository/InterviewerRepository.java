package com.exadel.project.interviewer.repository;

import com.exadel.project.subject.entity.Subject;
import com.exadel.project.interviewer.entity.Interviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface InterviewerRepository extends JpaRepository<Interviewer,Long>,JpaSpecificationExecutor<Interviewer> {
    Optional<Interviewer> findByEmail(String email);
    List<Interviewer> findAllBySubjectsIn(Collection<List<Subject>> subjects);
}

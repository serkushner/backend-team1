package com.exadel.project.interviewer.repository;

import com.exadel.project.internship.entity.Subject;
import com.exadel.project.interviewer.entity.Interviewer;
import com.exadel.project.interviewer.entity.InterviewerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface InterviewerRepository extends JpaRepository<Interviewer,Long>,JpaSpecificationExecutor<Interviewer> {
    List<Interviewer> findAllBySubjects(List<Subject> subjectList);
}

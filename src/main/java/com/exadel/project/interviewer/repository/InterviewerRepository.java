package com.exadel.project.interviewer.repository;

import com.exadel.project.interviewer.entity.Interviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InterviewerRepository extends JpaRepository<Interviewer,Long>,JpaSpecificationExecutor<Interviewer> {
}

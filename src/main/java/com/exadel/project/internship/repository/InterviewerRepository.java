package com.exadel.project.internship.repository;

import com.exadel.project.internship.entity.Interviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InterviewerRepository extends JpaRepository<Interviewer,Long>,JpaSpecificationExecutor<Interviewer> {
}

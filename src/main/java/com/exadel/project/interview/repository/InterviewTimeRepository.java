package com.exadel.project.interview.repository;

import com.exadel.project.interview.entity.InterviewTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InterviewTimeRepository extends JpaRepository<InterviewTime, Long>, JpaSpecificationExecutor<InterviewTime> {
}

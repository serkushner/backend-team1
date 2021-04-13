package com.exadel.project.trainee.repository;

import com.exadel.project.trainee.entity.InterviewPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InterviewPeriodRepository extends JpaRepository<InterviewPeriod, Long>, JpaSpecificationExecutor<InterviewPeriod> {
}

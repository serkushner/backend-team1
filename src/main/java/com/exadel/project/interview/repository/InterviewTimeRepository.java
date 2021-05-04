package com.exadel.project.interview.repository;

import com.exadel.project.interview.entity.InterviewTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InterviewTimeRepository extends JpaRepository<InterviewTime, Long>, JpaSpecificationExecutor<InterviewTime> {
    Optional<InterviewTime> findByStartDateAndEndDate(LocalDateTime startDate, LocalDateTime endDate);

    List<InterviewTime> findAllByIdIn(List<Long> ids);
}

package com.exadel.project.interview.repository;


import com.exadel.project.internship.entity.Internship;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.trainee.entity.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long>, JpaSpecificationExecutor<Interview> {
    List<Interview> findAllByInterviewerId(Long id);

    List<Interview> findAllByTraineeAndInternship(Trainee trainee, Internship internship);
}

package com.exadel.project.trainee.repository;

import com.exadel.project.trainee.entity.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TraineeRepository extends JpaRepository<Trainee, Long>,
        JpaSpecificationExecutor<Trainee> {
    Trainee findTraineeByEmail(String email);
}

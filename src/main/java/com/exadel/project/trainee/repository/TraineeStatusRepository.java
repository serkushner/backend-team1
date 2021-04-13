package com.exadel.project.trainee.repository;

import com.exadel.project.trainee.entity.TraineeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TraineeStatusRepository extends JpaRepository<TraineeStatus, Long>, JpaSpecificationExecutor<TraineeStatus> {
    TraineeStatus findTraineeStatusByName(String name);
}

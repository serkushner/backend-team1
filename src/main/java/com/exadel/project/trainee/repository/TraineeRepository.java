package com.exadel.project.trainee.repository;

import com.exadel.project.generic_service.GenericRepository;
import com.exadel.project.trainee.entity.Trainee;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends GenericRepository<Trainee,Long> {
}

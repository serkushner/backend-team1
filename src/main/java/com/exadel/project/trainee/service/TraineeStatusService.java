package com.exadel.project.trainee.service;

import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.trainee.entity.TraineeStatus;
import com.exadel.project.trainee.repository.TraineeStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TraineeStatusService extends BaseService<TraineeStatus, TraineeStatusRepository> {
    private final TraineeStatusRepository traineeStatusRepository;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }
}

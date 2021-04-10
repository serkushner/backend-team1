package com.exadel.project.trainee.repository;

import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.TraineeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Long>, JpaSpecificationExecutor<AdditionalInfo> {
}

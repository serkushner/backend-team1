package com.exadel.project.trainee.repository;

import com.exadel.project.internship.entity.Internship;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Long>, JpaSpecificationExecutor<AdditionalInfo> {
    AdditionalInfo findAdditionalInfoByInternshipAndTrainee(Internship internship, Trainee trainee);
}

package com.exadel.project.trainee.repository;

import com.exadel.project.internship.entity.Internship;
import com.exadel.project.subject.entity.Subject;
import com.exadel.project.trainee.entity.AdditionalInfo;
import com.exadel.project.trainee.entity.Trainee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Long>, JpaSpecificationExecutor<AdditionalInfo> {
    AdditionalInfo findAdditionalInfoByInternshipAndTrainee(Internship internship, Trainee trainee);
    List<AdditionalInfo> findAllByInternship_EndDateAfter(LocalDate localDate);
    List<AdditionalInfo> findAllByTraineeId(Long id);
    List<AdditionalInfo> findAllByTrainee_RecipientAndInternship_SubjectsIn(Boolean recipient, List<Subject> subjects);
}

package com.exadel.project.InternshipType.repository;

import com.exadel.project.InternshipType.entity.InternshipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface InternshipTypeRepository extends
        JpaRepository<InternshipType, Long>,
        JpaSpecificationExecutor<InternshipType> {
    Optional<InternshipType> findInternshipTypeByType(String type);
}

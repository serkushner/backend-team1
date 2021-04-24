package com.exadel.project.InternshipType.repository;

import com.exadel.project.InternshipType.entity.InternshipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InternshipTypeRepository extends
        JpaRepository<InternshipType, Long>,
        JpaSpecificationExecutor<InternshipType> {
    InternshipType findInternshipTypeByType(String type);
}

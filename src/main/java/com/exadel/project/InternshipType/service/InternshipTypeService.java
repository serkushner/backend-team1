package com.exadel.project.InternshipType.service;

import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.InternshipType.entity.InternshipType;
import com.exadel.project.InternshipType.repository.InternshipTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InternshipTypeService extends BaseService<InternshipType, InternshipTypeRepository> {

    private final InternshipTypeRepository internshipTypeRepository;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public InternshipType getByName(String name) {
        return internshipTypeRepository.findInternshipTypeByType(name);
    }
}

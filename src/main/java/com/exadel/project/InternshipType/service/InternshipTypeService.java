package com.exadel.project.InternshipType.service;

import com.exadel.project.InternshipType.entity.InternshipType;
import com.exadel.project.InternshipType.repository.InternshipTypeRepository;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> getAllInternshipTypesForInternshipForm() {
        return findBySpecifications(null, getSort("type")).stream().map(InternshipType::getType).collect(Collectors.toList());
    }
}

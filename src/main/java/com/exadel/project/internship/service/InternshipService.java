package com.exadel.project.internship.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.repository.rsql.RsqlSpecification;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.dto.InternshipDetailsDTO;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.mapper.InternshipDetailsMapper;
import com.exadel.project.internship.mapper.InternshipMapper;
import com.exadel.project.internship.repository.InternshipRepository;
import com.exadel.project.internship.service.rsql.InternshipRsqlSpecification;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InternshipService extends BaseService<Internship, InternshipRepository> {
    private static final String DEFAULT_SORTING_FIELD = "startDate";
    private final InternshipMapper internshipMapper;
    private final InternshipDetailsMapper internshipDetailsMapper;
    private final InternshipRsqlSpecification internshipRsqlSpecification;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        return internshipRsqlSpecification;
    }

    public List<InternshipDTO> getAll(String search, String sortFields) {
        Sort sort = getSort(sortFields);
        return super.getAllEntities(search, sort).stream()
                .map(internship -> internshipMapper.entityToDto(internship))
                .collect(Collectors.toList());
    }

    public InternshipDetailsDTO getById(Long id) throws EntityNotFoundException {
        return internshipDetailsMapper.entityToDto(super.getEntityById(id));
    }

    private Sort getSort(String sortFields){
        Sort sort = sortFields == null ? Sort.by(DEFAULT_SORTING_FIELD) : Sort.by(sortFields.split(","));
        return sort;
    }
}
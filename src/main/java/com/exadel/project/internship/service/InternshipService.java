package com.exadel.project.internship.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.dto.InternshipDetailsDTO;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.mapper.InternshipDetailsMapper;
import com.exadel.project.internship.mapper.InternshipMapper;
import com.exadel.project.internship.repository.InternshipRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InternshipService extends BaseService<Internship, InternshipRepository> {
    private final InternshipMapper internshipMapper;
    private final InternshipDetailsMapper internshipDetailsMapper;

    public List<InternshipDTO> getAll(String search) {
        return super.getAllEntities(search).stream().sorted(Comparator.comparing(Internship::getStartDate)).map(internship -> internshipMapper.entityToDto(internship))
                .collect(Collectors.toList());
    }


    public InternshipDetailsDTO getById(Long id) throws EntityNotFoundException {
        return internshipDetailsMapper.entityToDto(super.getEntityById(id));
    }
}
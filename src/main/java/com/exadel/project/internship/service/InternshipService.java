package com.exadel.project.internship.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.dto.InternshipInfoDTO;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.mapper.InternshipInfoMapper;
import com.exadel.project.internship.mapper.InternshipMapper;
import com.exadel.project.internship.repository.InternshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InternshipService extends BaseService<Internship, InternshipRepository> {
    InternshipMapper internshipMapper;
    InternshipInfoMapper internshipInfoMapper;

    public List<InternshipDTO> getAllDTOs(String search) {
        return super.getAllEntities(search).stream().sorted(Comparator.comparing(Internship::getStartDate)).map(internship -> internshipMapper.entityToDto(internship))
                .collect(Collectors.toList());
    }


    public InternshipInfoDTO getDTOById(Long id) throws EntityNotFoundException {
        return internshipInfoMapper.entityToDto(super.getEntityById(id));
    }
}
package com.exadel.project.internship.service;

import com.exadel.project.common.exception.EntityAlreadyExistsException;
import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.dto.InternshipDetailsDTO;
import com.exadel.project.internship.entity.Country;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.mapper.InternshipDetailsMapper;
import com.exadel.project.internship.mapper.InternshipMapper;
import com.exadel.project.internship.repository.InternshipRepository;
import com.exadel.project.internship.service.rsql.InternshipRsqlSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InternshipService extends BaseService<Internship, InternshipRepository> {

    @Autowired
    private final InternshipRepository repository;
    private final InternshipMapper internshipMapper;
    private final InternshipDetailsMapper internshipDetailsMapper;
    private final InternshipRsqlSpecification internshipRsqlSpecification;
    {
        defaultSortingField = "startDate";
        defaultSortingDirection = "desc";
    }

    @Override
    public RsqlSpecification getRsqlSpecification() {
        return internshipRsqlSpecification;
    }

//TODO refactor by DTO abstraction

    public List<InternshipDTO> getAll(String search, String sortFields) {
        Sort sort = getSort(sortFields);
        return super.findBySpecifications(search, sort).stream()
                .map(internship -> internshipMapper.entityToDto(internship))
                .collect(Collectors.toList());
    }

    public InternshipDetailsDTO getById(Long id) throws EntityNotFoundException {
        return internshipDetailsMapper.entityToDto(super.getEntityById(id));
    }

    public InternshipDetailsDTO addInternship(InternshipDetailsDTO internshipDetailsDTO) throws EntityAlreadyExistsException {
        if (checkExistence(internshipDetailsDTO)) {
            throw new EntityAlreadyExistsException();
        }
        Internship internship = internshipDetailsMapper.dtoToEntity(internshipDetailsDTO);
        //TODO possibly set something by default
        repository.saveAndFlush(internship);
        return internshipDetailsMapper.entityToDto(internship);
    }

    public InternshipDetailsDTO updateInternship(Long id, InternshipDetailsDTO internshipDetailsDTO) throws EntityNotFoundException {
        Internship internship = super.getEntityById(id);
        internshipDetailsMapper.updateInternship(internshipDetailsDTO, internship);
        repository.saveAndFlush(internship);
        return internshipDetailsMapper.entityToDto(internship);
    }

    public void deleteInternshipById(Long id) throws EntityNotFoundException {
        repository.delete(repository.getOne(id));
    }

    /**
     *
     * @param internshipDetailsDTO - full description of internship from UI
     * @return true, if internship exists
     */
    private boolean checkExistence(InternshipDetailsDTO internshipDetailsDTO) {
        boolean internshipExist = false;
        String title  = internshipDetailsDTO.getTitle();
        LocalDate startDate = internshipDetailsDTO.getStartDate();
        internshipExist = repository.findAllByNameAndStartDate(title, startDate).isPresent();
        return internshipExist;
    }
}
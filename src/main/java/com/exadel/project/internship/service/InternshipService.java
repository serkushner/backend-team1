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
import java.util.Optional;
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

    //TODO 19-04 implement here an addition based on the published column
    // and make it usable for both types due to the boolean parameter
    public List<InternshipDTO> getAll(String search, String sortFields) {
        Sort sort = getSort(sortFields);
        return super.findBySpecifications(search, sort).stream()
                .map(internship -> internshipMapper.entityToDto(internship))
                .collect(Collectors.toList());
    }

    public InternshipDetailsDTO getUnpostedById(Long id) throws EntityNotFoundException {
        return internshipDetailsMapper.entityToDto(repository.findByIdAndPublished(id, Boolean.FALSE));
    }

    public InternshipDetailsDTO getPostedById(Long id) throws EntityNotFoundException {
        return internshipDetailsMapper.entityToDto(repository.findByIdAndPublished(id, Boolean.TRUE));
    }

    public InternshipDetailsDTO updateUnpostedInternship(Long id,
            InternshipDetailsDTO internshipDetailsDTO) throws EntityNotFoundException {
        return updateInternship(id, internshipDetailsDTO, Boolean.FALSE);
    }

    public InternshipDetailsDTO updatePostedInternship(Long id,
            InternshipDetailsDTO internshipDetailsDTO) throws EntityNotFoundException {
        return updateInternship(id, internshipDetailsDTO, Boolean.TRUE);
    }

    private InternshipDetailsDTO updateInternship(Long id,
        InternshipDetailsDTO internshipDetailsDTO, Boolean isPublished) throws EntityNotFoundException {
        Internship internship = repository.findByIdAndPublished(id, isPublished);
        internshipDetailsMapper.updateInternship(internshipDetailsDTO, internship);
        repository.saveAndFlush(internship);
        return internshipDetailsMapper.entityToDto(internship);
    }

    public InternshipDetailsDTO addUnpostedInternship(InternshipDetailsDTO internshipDetailsDTO)
            throws EntityAlreadyExistsException {
        if (checkExistence(internshipDetailsDTO)) {
            throw new EntityAlreadyExistsException();
        }
        Internship internship = internshipDetailsMapper.dtoToEntity(internshipDetailsDTO);
        internship.setPublished(Boolean.FALSE);
        repository.saveAndFlush(internship);
        return internshipDetailsMapper.entityToDto(internship);
    }

    public void deleteUnpostedInternshipById(Long id) throws EntityNotFoundException {
        Internship internship = repository.findByIdAndPublished(id, Boolean.FALSE);
        if (internship == null) {
            throw new EntityNotFoundException();
        } else {
            repository.delete(internship);
        }
    }

    /**
     * @param internshipDetailsDTO - full description of internship from UI
     * @return true, if internship exists
     */
    private boolean checkExistence(InternshipDetailsDTO internshipDetailsDTO) {
        boolean internshipExist;
        String title  = internshipDetailsDTO.getTitle();
        LocalDate startDate = internshipDetailsDTO.getStartDate();
        Internship internship = repository.findInternshipByTitleAndStartDate(title, startDate);
        internshipExist = internship != null ? true : false;
        return internshipExist;
    }
}
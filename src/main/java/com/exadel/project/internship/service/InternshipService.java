package com.exadel.project.internship.service;

import com.exadel.project.InternshipType.entity.InternshipType;
import com.exadel.project.InternshipType.service.InternshipTypeService;
import com.exadel.project.common.exception.DoubleInternshipRegistrationException;
import com.exadel.project.common.exception.EntityAlreadyExistsException;
import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.country.entity.Country;
import com.exadel.project.country.service.CountryService;
import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.dto.InternshipDetailsDTO;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.internship.entity.Published;
import com.exadel.project.internship.mapper.InternshipDetailsMapper;
import com.exadel.project.internship.mapper.InternshipMapper;
import com.exadel.project.internship.repository.InternshipRepository;
import com.exadel.project.internship.service.rsql.InternshipRsqlSpecification;
import com.exadel.project.skill.entity.Skill;
import com.exadel.project.skill.service.SkillService;
import com.exadel.project.subject.entity.Subject;
import com.exadel.project.subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InternshipService extends BaseService<Internship, InternshipRepository> {

    private static final Logger logger = LoggerFactory.getLogger(InternshipService.class);
    @Autowired
    private final InternshipRepository repository;
    private final InternshipMapper internshipMapper;
    private final CountryService countryService;
    private final SubjectService subjectService;
    private final SkillService skillService;
    private final InternshipTypeService internshipTypeService;
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

    public InternshipDetailsDTO getUnpostedById(Long id) throws EntityNotFoundException {
        return internshipDetailsMapper.entityToDto(getInternshipByIdAndPublished(id, Published.VISIBLE_FOR_ADMINS));
    }

    public InternshipDetailsDTO getPostedById(Long id) throws EntityNotFoundException {
        return internshipDetailsMapper.entityToDto(getInternshipByIdAndPublished(id, Published.VISIBLE_FOR_INTERNS));
    }

    public InternshipDetailsDTO updateUnpostedInternship(Long id,
            InternshipDetailsDTO internshipDetailsDTO) throws EntityNotFoundException {
        return updateInternship(id, internshipDetailsDTO, Published.VISIBLE_FOR_ADMINS);
    }

    public InternshipDetailsDTO updatePostedInternship(Long id,
            InternshipDetailsDTO internshipDetailsDTO) throws EntityNotFoundException {
        return updateInternship(id, internshipDetailsDTO, Published.VISIBLE_FOR_INTERNS);
    }

    private InternshipDetailsDTO updateInternship(Long id,
                                                  InternshipDetailsDTO internshipDetailsDTO,
                                                  Published isPublished) throws EntityNotFoundException {
        Internship internship = getInternshipByIdAndPublished(id, isPublished);
        Internship internshipFromDto = getInternshipFromInternshipDetailsDto(internshipDetailsDTO);
        internshipFromDto.setId(internship.getId());
        internshipFromDto.setPublished(isPublished);
        repository.save(internshipFromDto);
        return internshipDetailsMapper.entityToDto(internshipFromDto);
    }

    private Internship getInternshipByIdAndPublished(Long id, Published isPublished) {
        return Optional.ofNullable(repository.findByIdAndPublished(id, isPublished))
                .orElseThrow(EntityNotFoundException::new);
    }

    private Internship getInternshipById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public InternshipDetailsDTO addUnpostedInternship(InternshipDetailsDTO dto)
            throws EntityAlreadyExistsException {
        checkDoubleRegistration(dto);
        Internship internship = getInternshipFromInternshipDetailsDto(dto);
        //TODO change on Published.VISIBLE_FOR_INTERNS for fast demo on ready landing
        internship.setPublished(Published.VISIBLE_FOR_ADMINS);
        repository.save(internship);
        return internshipDetailsMapper.entityToDto(internship);
    }

    private Internship getInternshipFromInternshipDetailsDto(InternshipDetailsDTO dto) {
        Internship internship = internshipDetailsMapper.dtoToEntity(dto);
        if (dto.getInternshipType() != null) {
            addInternshipTypeToInternship(dto.getInternshipType(), internship);
        }
        if (dto.getCountries() != null) {
            addCountriesToInternship(dto.getCountries(), internship);
        }
        if (dto.getSubjects() != null) {
            addSubjectsToInternship(dto.getSubjects(), internship);
        }
        if (dto.getSkills() != null) {
            addSkillsToInternship(dto.getSkills(), internship);
        }
        return internship;
    }

    private void addInternshipTypeToInternship(String internshipTypeName, Internship internship) {
        InternshipType type = internshipTypeService.getByName(internshipTypeName);
        internship.setInternshipType(type);
    }

    private void addCountriesToInternship(List<String> locations, Internship internship) {
        List<Country> countries = new ArrayList<>();
        for (String location : locations) {
            countries.add(countryService.getByName(location));
        }
        internship.setCountries(countries);
    }

    private void addSubjectsToInternship(List<String> subjectsNames, Internship internship) {
        List<Subject> subjects = new ArrayList<>();
        for (String name : subjectsNames) {
            subjects.add(subjectService.getByName(name));
        }
        internship.setSubjects(subjects);
    }

    private void addSkillsToInternship(List<String> skillsNames, Internship internship) {
        List<Skill> skills = new ArrayList<>();
        for (String name : skillsNames) {
            skills.add(skillService.getByName(name));
        }
        internship.setSkills(skills);
    }

    public void deleteUnpostedInternshipById(Long id) throws EntityNotFoundException {
        Internship internship = repository.findByIdAndPublished(id, Published.VISIBLE_FOR_ADMINS);
        if (internship == null) {
            throw new EntityNotFoundException();
        } else {
            repository.delete(internship);
        }
    }

    /**
     * @param internshipDetailsDTO - full description of internship from UI
     */
    private void checkDoubleRegistration(InternshipDetailsDTO internshipDetailsDTO) {
        String title  = internshipDetailsDTO.getTitle();
        LocalDate startDate = internshipDetailsDTO.getStartDate();
        if (title == null || startDate == null) {
            throw new IllegalArgumentException();
        }
        Internship internship = repository.findInternshipByTitleAndStartDate(title, startDate);
        if (internship != null){
            logger.debug("DoubleInternshipRegistrationException inside InternshipService");
            throw new DoubleInternshipRegistrationException();
        }
    }

    public List<InternshipDTO> getAllPosted(String search, String sortFields) {
        return getAll(search, sortFields, Published.VISIBLE_FOR_INTERNS);
    }

    public List<InternshipDTO> getAllUnposted(String search, String sortFields) {
        return getAll(search, sortFields, Published.VISIBLE_FOR_ADMINS);
    }

    public List<InternshipDTO> getAll(String search, String sortFields, Published isPublished) {
        Sort sort = getSort(sortFields);
        StringBuffer stringBuffer = new StringBuffer();
        if (search == null) {
            stringBuffer.append("published==").append(isPublished.toString());
        } else {
            stringBuffer.append(search);
            stringBuffer.append(";published==").append(isPublished.toString());
        }
        search = stringBuffer.toString();
        return super.findBySpecifications(search, sort).stream()
                .map(internship -> internshipMapper.entityToDto(internship))
                .collect(Collectors.toList());
    }

    public InternshipDetailsDTO approveUnpostedById(Long id) {
        return changePublisheddById(id, Published.VISIBLE_FOR_INTERNS);
    }


    public InternshipDetailsDTO disapproveUnpostedById(Long id) {
        return changePublisheddById(id, Published.VISIBLE_FOR_ADMINS);
    }

    private InternshipDetailsDTO changePublisheddById(Long id, Published published) {
        Internship internship = getInternshipById(id);
        internship.setPublished(published);
        repository.save(internship);
        return internshipDetailsMapper.entityToDto(internship);
    }

}
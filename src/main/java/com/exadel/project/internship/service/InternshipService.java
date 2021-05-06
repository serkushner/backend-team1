package com.exadel.project.internship.service;

import com.exadel.project.InternshipType.entity.InternshipType;
import com.exadel.project.InternshipType.service.InternshipTypeService;
import com.exadel.project.common.exception.DoubleInternshipRegistrationException;
import com.exadel.project.common.exception.EntityAlreadyExistsException;
import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.exception.PublishedStatusBadRequestException;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.country.entity.Country;
import com.exadel.project.country.service.CountryService;
import com.exadel.project.internship.dto.InternshipDTO;
import com.exadel.project.internship.dto.InternshipDetailsDTO;
import com.exadel.project.internship.entity.Format;
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
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class InternshipService extends BaseService<Internship, InternshipRepository> {

    private static final Logger logger = LoggerFactory.getLogger(InternshipService.class);
    @Autowired
    private final InternshipRepository internshipRepository;
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

    //public methods for interns and common access
    public List<InternshipDTO> getAllPosted(String search, String sortFields) {
        String validatedSearch = validateSearchInGetAllPosted(search);
        return getAll(validatedSearch, sortFields);
    }

    private String validateSearchInGetAllPosted(String search) {
        final String STATUS_POSTED = "published==VISIBLE_FOR_INTERNS";
        final String STATUS_UNPOSTED = "published==VISIBLE_FOR_ADMINS";
        boolean containsUnposted = false;
        boolean containsPosted = false;

        if (search != null) {
            //check, that search contains Published.VISIBLE_FOR_INTERNS or not mentioned
            containsUnposted = search.contains(STATUS_UNPOSTED);
            if (containsUnposted) {
                throw new PublishedStatusBadRequestException(
                        "Change internship status to published for interns in search filter to posted");
            }
            containsPosted = search.contains(STATUS_POSTED);
        }

        StringBuffer stringBuffer = new StringBuffer();
        if (!containsPosted) {
            if (search == null) {
                stringBuffer.append("published==").append(Published.VISIBLE_FOR_INTERNS.toString());
            } else {
                stringBuffer.append(search);
                stringBuffer.append(";published==").append(Published.VISIBLE_FOR_INTERNS.toString());;
            }
        }
        search = stringBuffer.toString();
        return search;
    }

    public InternshipDetailsDTO getPostedById(Long id) throws EntityNotFoundException {
        return internshipDetailsMapper.entityToDto(getInternshipByIdAndPublished(id, Published.VISIBLE_FOR_INTERNS));
    }

    public List<String> getAllFormatsOfInternships() {
        return Stream.of(Format.values())
                    .map(Format::name)
                    .collect(Collectors.toList());
    }

    //next public methods for admins only
    public InternshipDetailsDTO getById(Long id) throws EntityNotFoundException {
        return internshipDetailsMapper.entityToDto(getInternshipById(id));
    }

    public InternshipDetailsDTO updateUnpostedInternship(Long id,
                InternshipDetailsDTO internshipDetailsDTO) throws EntityNotFoundException {
        Internship internship = getInternshipById(id);
        if (internship.getPublished() == Published.VISIBLE_FOR_INTERNS) {
            throw new PublishedStatusBadRequestException(
                    "You can not change data in an internship with posted status. Change its status to published for admins.");
        }
        Internship internshipFromDto = getInternshipFromInternshipDetailsDto(internshipDetailsDTO);
        internshipFromDto.setId(internship.getId());
        internshipFromDto.setPublished(Published.VISIBLE_FOR_ADMINS);
        internshipRepository.save(internshipFromDto);
        return internshipDetailsMapper.entityToDto(internshipFromDto);
    }

    private Internship getInternshipByIdAndPublished(Long id, Published isPublished) {
        return Optional.ofNullable(internshipRepository.findByIdAndPublished(id, isPublished))
                .orElseThrow(EntityNotFoundException::new);
    }

    private Internship getInternshipById(Long id) {
        return internshipRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public InternshipDetailsDTO addUnpostedInternship(InternshipDetailsDTO dto) throws EntityAlreadyExistsException {
        checkDoubleRegistration(dto);
        Internship internship = getInternshipFromInternshipDetailsDto(dto);
        internship.setPublished(Published.VISIBLE_FOR_ADMINS);
        Internship createdInternship = internshipRepository.save(internship);
        return internshipDetailsMapper.entityToDto(createdInternship);
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
        internship.setInternshipType(internshipTypeService.getByName(internshipTypeName));
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
        Optional<Internship> internship = internshipRepository.findById(id);
        internship.orElseThrow(EntityNotFoundException::new);
        if (internship.get().getPublished() == Published.VISIBLE_FOR_ADMINS) {
            internshipRepository.delete(internship.get());
        } else {
            throw new PublishedStatusBadRequestException(
                    "Unavailable to delete the internship with the posted status.");
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
        Internship internship = internshipRepository.findInternshipByTitleAndStartDate(title, startDate);
        if (internship != null){
            logger.debug("DoubleInternshipRegistrationException inside InternshipService");
            throw new DoubleInternshipRegistrationException();
        }
    }

    public List<InternshipDTO> getAll(String search, String sortFields) {
        Sort sort = getSort(sortFields);
        return super.findBySpecifications(search, sort).stream()
                .map(internship -> internshipMapper.entityToDto(internship))
                .collect(Collectors.toList());
    }

    public InternshipDetailsDTO changeInternshipPublishedStatusById(Long id, String published) {
        boolean isVisibleForAdmins = false;
        boolean isVisibleForInterns = false;
        isVisibleForAdmins = Published.VISIBLE_FOR_ADMINS.toString().equals(published);
        if (!isVisibleForAdmins) {
            isVisibleForInterns = Published.VISIBLE_FOR_INTERNS.toString().equals(published);
        }
        if (isVisibleForAdmins) {
            return changePublishedById(id, Published.VISIBLE_FOR_ADMINS);
        } else if (isVisibleForInterns) {
            return changePublishedById(id, Published.VISIBLE_FOR_INTERNS);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private InternshipDetailsDTO changePublishedById(Long id, Published published) {
        Internship internship = getInternshipById(id);
        internship.setPublished(published);
        internshipRepository.save(internship);
        return internshipDetailsMapper.entityToDto(internship);
    }
}
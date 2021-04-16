package com.exadel.project.administrator.service;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.dto.RoleDto;
import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.administrator.entity.Role;
import com.exadel.project.administrator.mapper.AdministratorMapper;
import com.exadel.project.administrator.repository.AdministratorRepository;
import com.exadel.project.common.exception.EntityAlreadyExistsException;
import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.repository.TraineeRepository;
import com.exadel.project.trainee.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdministratorService extends BaseService<Administrator, AdministratorRepository> {

    private final AdministratorMapper administratorMapper;
    private final PasswordEncoder passwordEncoder;
    private final AdministratorRepository administratorRepository;
    private final TraineeRepository traineeRepository;
    private final TraineeService traineeService;

    {
        defaultSortingField = "surname";
        defaultSortingDirection = "desc";
    }

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public List<AdministratorDto> getAll(String search, String sortFields) {
        Sort sort = getSort(sortFields);
        return super.findBySpecifications(search, sort)
                .stream()
                .map(administratorMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public AdministratorDto getById(Long id) throws EntityNotFoundException {
        return administratorMapper.entityToDto(super.getEntityById(id));
    }

    public AdministratorDto addAdministrator(AdministratorDto administratorDto) throws EntityAlreadyExistsException {
        if (administratorRepository.findAdministratorByEmail(administratorDto.getEmail()) != null) {
            throw new EntityAlreadyExistsException();
        }
        //TODO check if login already exists
        Administrator administrator = administratorMapper.dtoToEntity(administratorDto);
        //administrator.setRole(Role.ADMIN);
        administratorRepository.save(administrator);
        return administratorMapper.entityToDto(administrator);
    }

    public AdministratorDto updateAdministrator(Long id, AdministratorDto administratorDto) throws EntityNotFoundException {
        Administrator administrator = findAdministratorById(id);
        administratorMapper.updateAdministrator(administratorDto, administrator);
        administratorRepository.save(administrator);
        return administratorMapper.entityToDto(administrator);
    }

    public AdministratorDto changeAdministratorRole(Long id, RoleDto role) throws EntityNotFoundException {
        Administrator existingAdministrator = findAdministratorById(id);
        existingAdministrator.setRole(Role.valueOf(role.getRole().toUpperCase()));
        administratorRepository.save(existingAdministrator);
        return administratorMapper.entityToDto(existingAdministrator);
    }

    public void deleteAdministratorById(Long id) throws EntityNotFoundException {
        administratorRepository.delete(findAdministratorById(id));
    }

    public AdministratorDto addTrainee(Long administratorId, Long traineeId) throws EntityNotFoundException {
        Trainee trainee = traineeService.getEntityById(traineeId);
        Administrator administrator = administratorMapper.dtoToEntity(getById(administratorId));
        if (trainee.getAdministrator() != null) {
            Administrator oldAdministrator = administratorMapper.dtoToEntity(getById(trainee.getAdministrator().getId()));
            oldAdministrator.getTrainees().remove(trainee);
            administratorRepository.save(oldAdministrator);
        }
        trainee.setAdministrator(administrator);
        traineeRepository.save(trainee);
        administrator.getTrainees().add(trainee);
        administratorRepository.save(administrator);
        return administratorMapper.entityToDto(administrator);
    }

    private Administrator findAdministratorById(Long id) throws EntityNotFoundException {
        return Optional.ofNullable(administratorRepository.findAdministratorById(id))
                .orElseThrow(()-> new EntityNotFoundException());
    }

}

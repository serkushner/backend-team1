package com.exadel.project.administrator.service;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.dto.ChangeRoleDto;
import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.administrator.mapper.AdministratorMapper;
import com.exadel.project.administrator.repository.AdministratorRepository;
import com.exadel.project.administrator.repository.RoleRepository;
import com.exadel.project.administrator.validator.AdministratorValidator;
import com.exadel.project.common.exception.EntityAlreadyExistsException;
import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.configurations.KeycloakConfigProperties;
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
    private final AdministratorValidator administratorValidator;
    private final PasswordEncoder passwordEncoder;
    private final AdministratorRepository administratorRepository;
    private final TraineeRepository traineeRepository;
    private final RoleRepository roleRepository;
    private final TraineeService traineeService;

    {
        defaultSortingField = "surname";
        defaultSortingDirection = "asc";
    }

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public List<AdministratorDto> findBySpecification(String search, String sortFields) {
        Sort sort = getSort(sortFields);
        return super.findBySpecifications(search, sort)
                .stream()
                .map(administratorMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public AdministratorDto getAdministratorByLogin(String login) throws EntityNotFoundException {
        return administratorMapper.entityToDto(findAdministratorByLogin(login));
    }

    public AdministratorDto getById(Long id) throws EntityNotFoundException {
        return administratorMapper.entityToDto(super.getEntityById(id));
    }

    public AdministratorDto addAdministrator(AdministratorDto administratorDto) throws EntityAlreadyExistsException {
        administratorValidator.checkAdministratorAlreadyExists(administratorDto);
        Administrator administrator = administratorMapper.dtoToEntity(administratorDto);
        administrator.getRoles().add(roleRepository.findByRoleName("ROLE_ADMIN"));
        administratorRepository.save(administrator);
        return administratorMapper.entityToDto(administrator);
    }

    public AdministratorDto updateAdministrator(Long id, AdministratorDto administratorDto) throws EntityNotFoundException {
        Administrator administrator = getEntityById(id);
        administratorMapper.updateAdministrator(administratorDto, administrator);
        administratorRepository.save(administrator);
        return administratorMapper.entityToDto(administrator);
    }

    public AdministratorDto changeAdministratorRole(Long id, ChangeRoleDto role) throws EntityNotFoundException {
        Administrator existingAdministrator = getEntityById(id);
        existingAdministrator.getRoles().add(roleRepository.findByRoleName(role.getRole().toUpperCase()));
        administratorRepository.save(existingAdministrator);
        return administratorMapper.entityToDto(existingAdministrator);
    }

    public void deleteAdministratorById(Long id) throws EntityNotFoundException {
        administratorRepository.delete(getEntityById(id));
    }

    public AdministratorDto addTraineeToAdministrator(Long administratorId, Long traineeId) {
        Trainee trainee = traineeService.getEntityById(traineeId);
        Administrator administrator = getEntityById(administratorId);
        trainee.setAdministrator(administrator);
        traineeRepository.save(trainee);
        return administratorMapper.entityToDto(administrator);
    }

    public boolean checkPassword(String username, String password) {
        if (passwordEncoder.matches(password, findAdministratorByLogin(username).getPassword())) {
            return true;
        }
        return false;
    }

    private Administrator findAdministratorByLogin(String login) throws EntityNotFoundException {
        return Optional.ofNullable(administratorRepository.findAdministratorByLogin(login))
                .orElseThrow(() -> new EntityNotFoundException());
    }

}

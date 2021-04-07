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
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministratorService extends BaseService<Administrator, AdministratorRepository> {

    private final AdministratorMapper administratorMapper;

    private final PasswordEncoder passwordEncoder;

    private final AdministratorRepository administratorRepository;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public AdministratorDto getAdministratorByEmail(String email) throws EntityNotFoundException {
        return administratorMapper.entityToDto(Optional.ofNullable(administratorRepository.findAdministratorByEmail(email))
                .orElseThrow(()-> new EntityNotFoundException()));
    }

    public AdministratorDto getAdministratorById(Long id) throws EntityNotFoundException {
        return administratorMapper.entityToDto(findAdministratorById(id));
    }

    public AdministratorDto addAdministrator(AdministratorDto administratorDto) throws EntityAlreadyExistsException {
        if (administratorRepository.findAdministratorByEmail(administratorDto.getEmail()) != null) {
            throw new EntityAlreadyExistsException();
        }
        Administrator administrator = administratorMapper.dtoToEntity(administratorDto);
        administrator.setRole(Role.ADMIN);
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

    private Administrator findAdministratorById(Long id) throws EntityNotFoundException {
        return Optional.ofNullable(administratorRepository.findAdministratorById(id))
                .orElseThrow(()-> new EntityNotFoundException());
    }

}

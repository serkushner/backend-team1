package com.exadel.project.administrator.service;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.administrator.entity.Role;
import com.exadel.project.administrator.mapper.AdministratorMapper;
import com.exadel.project.administrator.repository.AdministratorRepository;
import com.exadel.project.common.exception.EntityAlreadyExistsException;
import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministratorService extends BaseService<Administrator, AdministratorRepository> {

    private final AdministratorMapper administratorMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdministratorRepository administratorRepository;

    public AdministratorDto getAdministratorByEmail(String email) throws EntityNotFoundException {
        return administratorMapper.entityToDto(Optional.ofNullable(administratorRepository.findAdministratorByEmail(email))
                .orElseThrow(()-> new EntityNotFoundException()));
    }

    public AdministratorDto getAdministratorById(Long id) throws EntityNotFoundException {
        return administratorMapper.entityToDto(findAdministratorById(id));
    }

    public Administrator addAdministrator(AdministratorDto administratorDto) throws EntityAlreadyExistsException {
        if (administratorRepository.findAdministratorByEmail(administratorDto.getEmail()) != null) {
            throw new EntityAlreadyExistsException();
        }
        Administrator administrator = new Administrator();
        administrator.setLogin(administratorDto.getLogin());
        administrator.setEmail(administratorDto.getEmail());
        administrator.setName(administratorDto.getName());
        administrator.setSurname(administratorDto.getSurname());
        administrator.setPassword(passwordEncoder.encode(administratorDto.getPassword()));
        administrator.setRole(Role.ADMIN);
        administrator.setPhone(administratorDto.getPhone());
        administrator.setSkype(administratorDto.getSkype());
        return administratorRepository.save(administrator);
    }

    public void updateAdministrator(Long id, AdministratorDto administratorDto) throws EntityNotFoundException {
        Administrator administrator = findAdministratorById(id);
        if (StringUtils.isNotBlank(administratorDto.getLogin())) {
            administrator.setLogin(administratorDto.getLogin());
        }
        if (StringUtils.isNotBlank(administratorDto.getName())) {
            administrator.setName(administratorDto.getName());
        }
        if (StringUtils.isNotBlank(administratorDto.getSurname())) {
            administrator.setSurname(administratorDto.getSurname());
        }
        if (StringUtils.isNotBlank(administratorDto.getEmail())) {
            administrator.setEmail(administratorDto.getEmail());
        }
        if (StringUtils.isNotBlank(administratorDto.getPassword())) {
            administrator.setPassword(passwordEncoder.encode(administratorDto.getPassword()));
        }
        if (StringUtils.isNotBlank(administratorDto.getPhone())) {
            administrator.setPhone(administratorDto.getPhone());
        }
        if (StringUtils.isNotBlank(administratorDto.getSkype())) {
            administrator.setSkype(administratorDto.getSkype());
        }
        administratorRepository.save(administrator);
    }

    public void changeAdministratorRole(Long id, String roleName) throws EntityNotFoundException {
        Administrator existingAdministrator = findAdministratorById(id);
        existingAdministrator.setRole(Role.valueOf(roleName.toUpperCase()));
        administratorRepository.save(existingAdministrator);
    }

    public void deleteAdministratorById(Long id) throws EntityNotFoundException {
        administratorRepository.delete(findAdministratorById(id));
    }

    //TODO changeAdministratorRole

    private Administrator findAdministratorById(Long id) throws EntityNotFoundException {
        return Optional.ofNullable(administratorRepository.findAdministratorById(id))
                .orElseThrow(()-> new EntityNotFoundException());
    }

}

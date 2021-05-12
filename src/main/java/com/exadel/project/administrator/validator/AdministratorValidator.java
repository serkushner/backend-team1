package com.exadel.project.administrator.validator;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.repository.AdministratorRepository;
import com.exadel.project.common.exception.EntityAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdministratorValidator {

    private final AdministratorRepository administratorRepository;

    public void checkAdministratorAlreadyExists(AdministratorDto administratorDto) {
        if (administratorRepository.findAdministratorByEmail(administratorDto.getEmail()) != null
                || administratorRepository.findAdministratorByLogin(administratorDto.getLogin()) != null) {
            throw new EntityAlreadyExistsException();
        }
    }

}

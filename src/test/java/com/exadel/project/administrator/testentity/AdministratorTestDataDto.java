package com.exadel.project.administrator.testentity;

import com.exadel.project.administrator.dto.AdministratorDto;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AdministratorTestDataDto {

    public AdministratorDto getAdministratorDto(Long id, String login){
        AdministratorDto administratorDto = new AdministratorDto();
        administratorDto.setId(id);
        administratorDto.setLogin(login);
        administratorDto.setName("Keanu");
        administratorDto.setSurname("Reeves");
        administratorDto.setEmail("neo.forever@gmail.com");
        administratorDto.setPhone("1213675456");
        administratorDto.setRoles(Collections.emptySet());
        administratorDto.setPassword("111");
        administratorDto.setSkype("neo_the_best");
        return administratorDto;
    }

}

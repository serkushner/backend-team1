package com.exadel.project.unit.administrator.testentity;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.administrator.entity.Role;
import org.springframework.stereotype.Component;

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
        administratorDto.setRole(Role.ADMIN);
        administratorDto.setPassword("111");
        administratorDto.setSkype("neo_the_best");
        return administratorDto;
    }

}

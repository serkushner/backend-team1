package com.exadel.project.administrator.testentity;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.administrator.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class AdministratorTestDataDto {

    public AdministratorDto getAdministratorDto(){
        AdministratorDto administratorDto = new AdministratorDto();
        administratorDto.setId(1L);
        administratorDto.setLogin("Neo");
        administratorDto.setName("Keanu");
        administratorDto.setSurname("Reeves");
        administratorDto.setEmail("neo.forever@gmail.com");
        administratorDto.setPhone("1213675456");
        administratorDto.setRole(Role.ADMIN);
        administratorDto.setPassword("111");
        administratorDto.setSkype("neo_the_best");
        return administratorDto;
    }

    public AdministratorDto getSecondAdministratorDto(){
        AdministratorDto administratorSecondDto = new AdministratorDto();
        administratorSecondDto.setId(2L);
        administratorSecondDto.setLogin("Smith");
        administratorSecondDto.setName("Hugo");
        administratorSecondDto.setSurname("Weaving");
        administratorSecondDto.setEmail("smith.forever@gmail.com");
        administratorSecondDto.setPhone("44444444");
        administratorSecondDto.setRole(Role.ADMIN);
        administratorSecondDto.setPassword("222");
        administratorSecondDto.setSkype("smith_the_best");
        return administratorSecondDto;
    }

}

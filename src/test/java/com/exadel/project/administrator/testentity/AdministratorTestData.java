package com.exadel.project.administrator.testentity;

import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.administrator.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class AdministratorTestData {

    public Administrator getAdministrator(){
        Administrator administrator = new Administrator();
        administrator.setId(1L);
        administrator.setLogin("Neo");
        administrator.setName("Keanu");
        administrator.setSurname("Reeves");
        administrator.setEmail("neo.forever@gmail.com");
        administrator.setPhone("1213675456");
        administrator.setRole(Role.ADMIN);
        administrator.setPassword("111");
        administrator.setSkype("neo_the_best");
        return administrator;
    }

    public Administrator getSecondAdministrator(){
        Administrator administrator = new Administrator();
        administrator.setId(2L);
        administrator.setLogin("Smith");
        administrator.setName("Hugo");
        administrator.setSurname("Weaving");
        administrator.setEmail("smith.forever@gmail.com");
        administrator.setPhone("44444444");
        administrator.setRole(Role.ADMIN);
        administrator.setPassword("222");
        administrator.setSkype("smith_the_best");
        return administrator;
    }

}

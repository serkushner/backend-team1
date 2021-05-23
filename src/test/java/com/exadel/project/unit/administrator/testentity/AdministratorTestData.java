package com.exadel.project.unit.administrator.testentity;

import com.exadel.project.administrator.entity.Administrator;
import org.springframework.stereotype.Component;

@Component
public class AdministratorTestData {

    public Administrator getAdministrator(Long id, String login){
        Administrator administrator = new Administrator();
        administrator.setId(id);
        administrator.setLogin(login);
        administrator.setName("Keanu");
        administrator.setSurname("Reeves");
        administrator.setEmail("neo.forever@gmail.com");
        administrator.setPhone("1213675456");
        administrator.setPassword("111");
        administrator.setSkype("neo_the_best");
        return administrator;
    }

}

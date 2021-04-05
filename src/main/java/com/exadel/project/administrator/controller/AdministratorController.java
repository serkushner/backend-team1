package com.exadel.project.administrator.controller;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.administrator.service.AdministratorService;
import com.exadel.project.common.exception.EntityAlreadyExistsException;
import com.exadel.project.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin/")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    private static final String ID = "/{id}";

    @GetMapping(value = ID)
    public AdministratorDto getAdmin(@PathVariable Long id) {
        try {
            return administratorService.getAdministratorById(id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = ID +"/delete")
    public boolean deleteAdmin(@PathVariable Long id) {
        try {
            administratorService.deleteAdministratorById(id);
            return true;
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Administrator addAdministrator(@RequestBody AdministratorDto dto) {
        try {
            return administratorService.addAdministrator(dto);
        } catch (EntityAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping(value = ID +"/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateAdministrator(@PathVariable Long id, @RequestBody AdministratorDto dto) {
        try {
            administratorService.updateAdministrator(id, dto);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @PostMapping(value = ID +"/changerole")
    public void changeAdministratorRole(@PathVariable Long id, @RequestBody String roleName) {
        try {
            administratorService.changeAdministratorRole(id, roleName);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}

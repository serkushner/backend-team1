package com.exadel.project.administrator.controller;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.dto.RoleDto;
import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.administrator.service.AdministratorService;
import com.exadel.project.common.exception.EntityAlreadyExistsException;
import com.exadel.project.common.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdministratorController {

    private final AdministratorService administratorService;

    private static final String ID = "/{id}";

    @GetMapping(value = ID)
    public ResponseEntity<AdministratorDto> getAdmin(@PathVariable Long id) {
            return ResponseEntity.ok(administratorService.getAdministratorById(id));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        administratorService.deleteAdministratorById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<AdministratorDto> addAdministrator(@RequestBody AdministratorDto dto) {
            return ResponseEntity.ok(administratorService.addAdministrator(dto));
    }

    @PutMapping(value = ID)
    public ResponseEntity<AdministratorDto> updateAdministrator(@PathVariable Long id, @RequestBody AdministratorDto dto) {
        return ResponseEntity.ok(administratorService.updateAdministrator(id, dto));
    }

    @PutMapping(value ="/role" + ID)
    public ResponseEntity<AdministratorDto> changeAdministratorRole(@PathVariable Long id, @RequestBody RoleDto role) {
        return ResponseEntity.ok(administratorService.changeAdministratorRole(id, role));
    }

}

package com.exadel.project.administrator.controller;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.dto.RoleDto;
import com.exadel.project.administrator.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdministratorController {

    private final AdministratorService administratorService;

    private static final String ID = "/{id}";

    @GetMapping
    public ResponseEntity<List<AdministratorDto>> findBySpecification(@RequestParam(value = "search", required = false) String search,
                                                                       @RequestParam(value = "sort", required = false) String sort) {
            return ResponseEntity.ok(administratorService.findBySpecification(search, sort));
    }

    @GetMapping(value = ID)
    public ResponseEntity<AdministratorDto> getAdmin(@PathVariable Long id) {
        return ResponseEntity.ok(administratorService.getById(id));
    }

    //@Secured("ROLE_SUPERADMIN")
    @DeleteMapping(value = ID)
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        administratorService.deleteAdministratorById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdministratorDto> addAdministrator(@RequestBody AdministratorDto dto) {
            return ResponseEntity.ok(administratorService.addAdministrator(dto));
    }

    @PutMapping(value = ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdministratorDto> updateAdministrator(@PathVariable Long id, @RequestBody AdministratorDto dto) {
        return ResponseEntity.ok(administratorService.updateAdministrator(id, dto));
    }

    @PutMapping(value ="/role" + ID)
    public ResponseEntity<AdministratorDto> changeAdministratorRole(@PathVariable Long id, @RequestBody RoleDto role) {
        return ResponseEntity.ok(administratorService.changeAdministratorRole(id, role));
    }

}

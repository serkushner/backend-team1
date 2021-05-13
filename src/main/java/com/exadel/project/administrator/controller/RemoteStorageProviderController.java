package com.exadel.project.administrator.controller;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/verify")
@RequiredArgsConstructor
public class RemoteStorageProviderController {

    private final AdministratorService administratorService;

    @GetMapping("/{username}")
    public ResponseEntity<AdministratorDto> getAdmin(@PathVariable("username") String username) {
        return ResponseEntity.ok(administratorService.getAdministratorByLogin(username));
    }

    @PostMapping("/{username}")
    public ResponseEntity<Boolean> verifyAdministratorPassword(@PathVariable("username") String username, @RequestBody String password) {
        return ResponseEntity.ok(administratorService.checkPassword(username, password));
    }

}

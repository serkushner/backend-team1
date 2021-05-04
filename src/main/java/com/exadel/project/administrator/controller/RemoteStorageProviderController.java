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

    @GetMapping("/{login}")
    public ResponseEntity<AdministratorDto> getAdmin(@PathVariable String login) {
        return ResponseEntity.ok(administratorService.getAdministratorByLogin(login));
    }

    @PostMapping("/{login}")
    public ResponseEntity<Boolean> verifyAdministratorPassword(@PathVariable String login, @RequestBody String password) {
        return ResponseEntity.ok(administratorService.checkPassword(login, password));
    }

}

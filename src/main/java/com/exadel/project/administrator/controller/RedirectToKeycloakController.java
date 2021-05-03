package com.exadel.project.administrator.controller;

import com.exadel.project.administrator.dto.CredentialsDto;
import com.exadel.project.administrator.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/signin")
@RequiredArgsConstructor
public class RedirectToKeycloakController {

    private final AdministratorService administratorService;

    @PostMapping
    public ResponseEntity<String> redirectToKeycloak(@RequestBody CredentialsDto dto) throws IOException {
        return ResponseEntity.ok(administratorService.redirectToKeycloak(dto.getUsername(), dto.getPassword()));
    }

}

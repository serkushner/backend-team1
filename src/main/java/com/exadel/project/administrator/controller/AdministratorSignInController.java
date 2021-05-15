package com.exadel.project.administrator.controller;

import com.exadel.project.administrator.dto.CredentialsDto;
import com.exadel.project.administrator.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/signin")
@RequiredArgsConstructor
public class AdministratorSignInController {

    private final AdministratorService administratorService;

    /*@PostMapping
    public ResponseEntity<String> administratorSignIn(@RequestBody CredentialsDto dto) throws IOException {
        return ResponseEntity.ok(administratorService.redirectToKeycloak(dto.getUsername(), dto.getPassword()));
    }*/

    @GetMapping
    public ResponseEntity<Void> administratorSignIn() throws IOException {
        return ResponseEntity.noContent().build();
    }

}

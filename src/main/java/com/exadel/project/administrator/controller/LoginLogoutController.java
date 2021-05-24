package com.exadel.project.administrator.controller;

import com.exadel.project.administrator.dto.CredentialsDto;
import com.exadel.project.administrator.entity.LoginLogoutResponse;
import com.exadel.project.administrator.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginLogoutController {

    private final AdministratorService administratorService;

    /*@GetMapping
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login success");
    }*/


    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<LoginLogoutResponse> processLogin(@RequestBody CredentialsDto dto) {
        return ResponseEntity.ok(administratorService.login(dto));
    }

    @GetMapping(value = "/authout")
    @ResponseBody
    public ResponseEntity<LoginLogoutResponse> processLogout() {
        return ResponseEntity.ok(administratorService.logout());
    }
}

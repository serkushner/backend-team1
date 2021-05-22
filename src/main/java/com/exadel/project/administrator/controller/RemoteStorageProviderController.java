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
<<<<<<< HEAD
    public ResponseEntity<AdministratorDto> getAdmin(@PathVariable String username) {
=======
    public ResponseEntity<AdministratorDto> getAdmin(@PathVariable("username") String username) {
>>>>>>> master
        return ResponseEntity.ok(administratorService.getAdministratorByLogin(username));
    }

    @PostMapping("/{username}/password")
<<<<<<< HEAD
    public ResponseEntity<Boolean> verifyAdministratorPassword(@PathVariable String username, @RequestBody String password) {
=======
    public ResponseEntity<Boolean> verifyAdministratorPassword(@PathVariable("username") String username, @RequestBody String password) {
>>>>>>> master
        return ResponseEntity.ok(administratorService.checkPassword(username, password));
    }

}

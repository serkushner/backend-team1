package com.exadel.project.common.controller;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.service.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AddTraineeController {

    private final AdministratorService administratorService;

    private static final String ID = "/{id}";

    @PutMapping(value = "/trainee-to-admin" + ID)
    public ResponseEntity<AdministratorDto> addTrainee(@PathVariable Long id, @RequestBody String traineeId) {
        return ResponseEntity.ok(administratorService.addTrainee(id, Long.parseLong(traineeId)));
    }

}

package com.exadel.project.trainee.controller;

import com.exadel.project.trainee.service.ConfirmTraineeEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConfirmTraineeEmailController {

    private final ConfirmTraineeEmailService confirmTraineeEmailService;

    @PostMapping("email/{token}")
    public ResponseEntity<Boolean> confirmTraineeEmailByToken(@PathVariable String token) {
        return ResponseEntity.ok(confirmTraineeEmailService.confirmTraineeEmail(token));
    }

}

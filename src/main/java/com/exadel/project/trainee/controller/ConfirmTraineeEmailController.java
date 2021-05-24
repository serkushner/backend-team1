package com.exadel.project.trainee.controller;

import com.exadel.project.trainee.service.ConfirmTraineeEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class ConfirmTraineeEmailController {

    private final ConfirmTraineeEmailService confirmTraineeEmailService;

    @PostMapping("email/{token}")
    public ModelAndView confirmTraineeEmailByToken(@PathVariable String token) {
        confirmTraineeEmailService.confirmTraineeEmail(token);
        return new ModelAndView("confirmed");
    }
}

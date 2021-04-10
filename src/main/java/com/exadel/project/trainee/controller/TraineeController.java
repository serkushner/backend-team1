package com.exadel.project.trainee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("internship")
@RequiredArgsConstructor
public class TraineeController {

    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadCv(@RequestParam Mul)
}

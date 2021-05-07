package com.exadel.project.InternshipType.controller;

import com.exadel.project.InternshipType.service.InternshipTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("internship-type")
@RequiredArgsConstructor
public class InternshipTypeController {

    private final InternshipTypeService internshipTypeService;

    @GetMapping
    public ResponseEntity<List<String>> getAllInternshipTypes() {
        return ResponseEntity.ok(internshipTypeService.getAllInternshipTypesForInternshipForm());
    }
}
